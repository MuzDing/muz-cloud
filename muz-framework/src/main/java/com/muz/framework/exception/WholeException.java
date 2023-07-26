package com.muz.framework.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Order(1)
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class WholeException {

    static{
        //log.info("加载全局异常捕获类" );
    }

    /**
     * 返回异常包装信息
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public Object exceptionHandler(Exception e){
        //log.error("error:",e);
//        ApiResult result = new ApiResult(Constants.FAILED);
//        result.setMsg(e.getMessage());
//        return result;
        return null;
    }
}
