package com.oneapm.alter.controller;

import com.oneapm.alter.model.base.BaseRepEnt;
import com.oneapm.alter.service.ApmAlertService;
import com.oneapm.alter.utl.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Slf4j
@RestController
@RequestMapping(value="/apmAlter")
public class ApmAlterController
{
	@Autowired
	private ApmAlertService apmAlertService;

	@Value("${code.aiCode}")
	private  String aiDefaultCode;

	@Value("${code.aiCode}")
	private String biDefaultCode;

	@ExceptionHandler(value = BaseException.class)
	@PostMapping(value="/aiAdd")
	public BaseRepEnt aiAdd(@RequestBody String reqBody ,@RequestParam( value="sourceCode", defaultValue="" ) String sourceCode ) throws Exception
	{
		HashMap<String,Object> reqParams = new HashMap<>();
		reqParams.put("reqBody",reqBody);
		if(StringUtils.isBlank(sourceCode)){
			reqParams.put("sourceCode",aiDefaultCode);
		}else{
			reqParams.put("sourceCode",sourceCode);
		}
		return apmAlertService.dealAiAlert(reqParams);

	}

	@ExceptionHandler(value = BaseException.class)
	@PostMapping(value="/biAdd")
	public BaseRepEnt biAdd(@RequestBody String reqBody ,
						@RequestParam(value="sourceCode", defaultValue="" ) String sourceCode )
			throws Exception
	{
		HashMap<String,Object> reqParams = new HashMap<>();
		reqParams.put("reqBody",reqBody);
		if(StringUtils.isBlank(sourceCode)){
			reqParams.put("sourceCode",biDefaultCode);
		}else{
			reqParams.put("sourceCode",sourceCode);
		}
		//因为AI 和BI告警的传参处理一致目前仅引用一个
		return apmAlertService.dealAiAlert(reqParams);
	}
	
}
