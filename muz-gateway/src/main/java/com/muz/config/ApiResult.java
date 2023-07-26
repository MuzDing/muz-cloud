package com.muz.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description 所有接口调用返回的统一包装结果类
 * @Author jianglong
 * @Date 2020/05/14
 * @Version V1.0
 */
@Data
public class ApiResult<T> implements java.io.Serializable {

    private String code;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private String msg = "";
    private T data;

    public ApiResult(){
        this.code = "1";
    }

    public ApiResult(final String code){
        this.code = code;
    }

    public ApiResult(final String code, final T data){
        this.code = code;
        this.data = data;
    }

    public ApiResult(final String code, final String msg, final T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResult(final T data){
        this.code = "1";
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp == null ? new Date(): timestamp;
    }
}
