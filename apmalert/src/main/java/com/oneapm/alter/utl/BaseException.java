package com.oneapm.alter.utl;

import com.oneapm.alter.model.base.BaseRepEnt;
import com.oneapm.alter.model.base.ErrRepEnt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zou on 2020/3/23.
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class BaseException extends Exception {

    @ExceptionHandler(Exception.class)
    public BaseRepEnt exceptionHandler(Exception e) {
        ErrRepEnt repEnt = new ErrRepEnt();
        repEnt.setData(null);
        repEnt.setRepsonseCode(500);
        repEnt.setMsg(e.getMessage());
        log.error("error stack is ", e);
        //todo 正常情况下 每种异常都应该有个异常处理方法
        return repEnt;

    }

}
