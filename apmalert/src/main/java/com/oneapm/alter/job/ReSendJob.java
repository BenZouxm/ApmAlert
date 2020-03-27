package com.oneapm.alter.job;
import com.oneapm.alter.service.impl.ApmAlertServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import java.util.Date;




@Service
@Slf4j
public class ReSendJob implements BaseJob {


    public ReSendJob() {
          
    }

    @Autowired
    private ApmAlertServiceImpl apmAlertService;


    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
        log.error("ReSendJob Job 开始执行时间: " + new Date());
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        apmAlertService.sendUnSuccessRecord();
        log.error("ReSendJob Job 执行结束时间为:"+ new Date());
    }

}  
