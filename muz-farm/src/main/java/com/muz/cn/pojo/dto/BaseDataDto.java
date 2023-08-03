package com.muz.cn.pojo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BaseDataDto<T>{
    @NotNull
    private Integer optCode;
    private T data;

}
