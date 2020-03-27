package com.oneapm.alter.service.impl;

import com.oneapm.alter.dao.AlertRecordMapper;
import com.oneapm.alter.entity.AlertRecord;
import com.oneapm.alter.model.base.BaseRepEnt;
import com.oneapm.alter.model.AiAlertRecord;
import com.oneapm.alter.model.TaiYueRecord;
import com.oneapm.alter.service.ApmAlertService;
import com.oneapm.alter.utl.DateUtil;
import com.oneapm.alter.utl.HttpClient;
import com.oneapm.alter.utl.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zou on 2020/3/24.
 */
@Slf4j
@Service
public class ApmAlertServiceImpl implements ApmAlertService{

    @Value("${request.url}")
    public String requestUrl;

    @Value("${request.port}")
    public String port;

    @Value("${request.queryParam}")
    private String queryParam;

    @Value("${request.overSeconds}")
    private String overSeconds;

    @Value("${request.reSendTimes}")
    private String reSendTimes;



    @Autowired
    private AlertRecordMapper alertRecordMapper;

    private static final HashMap<String, String> alertObjectStatusMap = new HashMap<String, String>();
    static {
        // 映射泰岳的告警
        //严重告警-->次要告警，警告--》警告告警 ，通知---》恢复告警
        //alarm 严重告警
        //  major 主要告警
       // minor 次要告警
        //warn 警告告警
        //ok 恢复告警
        alertObjectStatusMap.put("警告","warn");
        alertObjectStatusMap.put("严重","minor");
        alertObjectStatusMap.put("正常","ok");
    }


    @Override
    public BaseRepEnt dealAiAlert(HashMap<String, Object> reqs){
        String pushurl = requestUrl;
        String repBody = (String)reqs.get("reqBody");
        log.info("Recived AlertRecord Data: "+repBody);
        int sourceCode = Integer.parseInt((String)reqs.get("sourceCode"));
        //convert the ai
        AiAlertRecord aiAlertRecord = (AiAlertRecord) JsonConverter.jsonToObject(repBody,AiAlertRecord.class);
        String jsonStr = buildTaiYueAlertBoby(aiAlertRecord,sourceCode);
        //init alertRcord and prepare to save into the db
        AlertRecord alertRecord = new AlertRecord();
        alertRecord.setAlertSoureCode(sourceCode);
        Date currentDate = new Date();
        alertRecord.setRecvTime(currentDate);
        alertRecord.setSendTime(currentDate);
        alertRecord.setAlertId(aiAlertRecord.getEventId());
        alertRecord.setRecord(jsonStr);
        alertRecord.setSend(false);
        BaseRepEnt baseRepEnt = new BaseRepEnt();
        try{
            HashMap<String,Object> result = HttpClient.sendApplicationByPost(requestUrl,queryParam,jsonStr);
            alertRecord.setSendRep(currentDate.toString()+":"+result.get("status"));
            if("200".equals(result.get("status")+"")){
                //对于发送成功的将发送成功标志位标记为true
                alertRecord.setSend(true);
            }
            //对于非中间件造成的传输失败，统一返回成功
            baseRepEnt.setResult(true);
            baseRepEnt.setMsg("success");
        }catch (Exception e){
            alertRecord.setSendRep(currentDate.toString()+":"+e.getMessage());
            alertRecord.setSend(false);
            baseRepEnt.setResult(false);
            log.error("running error ,the stack is",e);
        }finally {
            int saveRes = alertRecordMapper.saveAlterRecord(alertRecord);
            if(saveRes<1){
                log.error("数据库插入记录失败");
            }
        }
        return baseRepEnt;
    }

    @Override
    public BaseRepEnt dealBiAlert(HashMap<String, Object> reqs) {
        log.info("Recived BI Data: ",reqs.get("reqBody"));
        return null;
    }



    private String buildTaiYueAlertBoby(AiAlertRecord aiAlertRecord,int sourceCode){
        //todo 因AI 和 BI中 Object中存在一条数据差异，剩余的为一致，如需做适配根据传入的sourceCode
        TaiYueRecord taiYueRecord = new TaiYueRecord();
        taiYueRecord.setHost(aiAlertRecord.getAppName());
        //根据tierName 转换成对应的taiyue class
        taiYueRecord.setClassAlter( aiAlertRecord.getAlertRule()+"");
        taiYueRecord.setInstance(aiAlertRecord.getAlertObject().get("agentName")+"");
        taiYueRecord.setOccurtime(aiAlertRecord.getCreateTime()+"");
        taiYueRecord.setParameter(aiAlertRecord.getRuleContent().toString()+";"+aiAlertRecord.getRealTimeIndex().toString());
        //告警映射
        taiYueRecord.setStatus(alertObjectStatusMap.get((aiAlertRecord.getAlertObjectStatus()+"").trim()));
        //泰岳资源搜索类型 5 用 instance 值做 SearchCode 查资源
        // 2 用 host 值做 MONAME 查资源
        // 1 用 host 值做 SearchCode 查资源
        //当前不确定直接用所传入instance的值agentId 作为搜索参考
        taiYueRecord.setRessearchtype("5");
        return JsonConverter.beanToJson(taiYueRecord);
    }

    private List<AlertRecord> getUnSendRecordList(){
        Date beginDate = DateUtil.calOverDate(overSeconds);
        return alertRecordMapper.getUnsentAlertRecord(beginDate,Integer.parseInt(reSendTimes));
    }

    public void sendUnSuccessRecord(){
        List<AlertRecord> alertRecords = getUnSendRecordList();
        for(AlertRecord alertRecord : alertRecords){
            try{
                HashMap<String,Object> result = HttpClient.sendApplicationByPost(requestUrl,queryParam,alertRecord.getRecord());
                String tempRep = alertRecord.getSendRep();
                alertRecord.setSendRep(new Date().toString()+":"+result.get("status")+";"+tempRep);
                //重发次数
                alertRecord.setReSendTimes(Integer.parseInt(alertRecord.getReSendTimes()+"")+1);
                alertRecord.setSendTime(new Date());
                if("200".equals(result.get("status")+"")){
                    //对于发送成功的将发送成功标志位标记为true
                    alertRecord.setSend(true);
                }
                alertRecordMapper.updateAlterRecord(alertRecord);
            }catch(Exception e) {
                log.error("send has error reocord is"+alertRecord.getRecord(),e);
            }
    }
    }


}
