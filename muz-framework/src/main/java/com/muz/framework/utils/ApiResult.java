package com.muz.framework.utils;

import lombok.Data;

@Data
public class ApiResult<T> {

    private Integer code = 200;
    private String msg = "success";
    private T data;
}
