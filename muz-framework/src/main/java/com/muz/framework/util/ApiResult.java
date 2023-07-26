package com.muz.framework.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResult<T> implements java.io.Serializable {

    private Integer code;
    private LocalDateTime timestamp;
    private String msg = "";
    private T data;
}
