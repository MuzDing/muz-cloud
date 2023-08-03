package com.muz.cn.config;

import com.muz.framework.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult<Object> handleException(Exception e) {
        ApiResult apiResult = new ApiResult<>();
        e.printStackTrace();
        // 处理异常，可以根据异常类型设置不同的响应状态码和错误信息
        if (e instanceof MethodArgumentNotValidException) {
            // 参数检验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            Map<String, String> map = new HashMap<>();
            BindingResult result = methodArgumentNotValidException.getBindingResult();
            result.getFieldErrors().forEach((item)->{
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
            log.error("数据校验出现错误：", e);
            apiResult.setCode(500);
            apiResult.setData(map);
            return apiResult;
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            apiResult.setCode(HttpStatus.METHOD_NOT_ALLOWED.value());
            apiResult.setMsg("请求方法错误");
            return apiResult;
        } else if (e instanceof HttpMessageNotReadableException) {
            apiResult.setCode(HttpStatus.BAD_REQUEST.value());
            apiResult.setMsg("请求参数缺失");
            return apiResult;
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            apiResult.setMsg("请求参数类型错误");
            apiResult.setCode(HttpStatus.BAD_REQUEST.value());
            return apiResult;
        } else if (e instanceof NoHandlerFoundException) {
            NoHandlerFoundException ex = (NoHandlerFoundException) e;
            apiResult.setMsg("请求地址不存在");
            apiResult.setCode(HttpStatus.NOT_FOUND.value());
            return apiResult;
        } else {
            //如果是系统的异常，比如空指针这些异常
            apiResult.setMsg("系统异常");
            apiResult.setCode(5000);
            return apiResult;
        }
    }
}
