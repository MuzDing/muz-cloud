package com.muz.cn.pojo.bo.Weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class Rain {
    // (如果有)过去 1 小时的雨量，毫米。请注意，该参数仅可使用 mm 作为测量单位
    @JsonProperty("1h")
    private double oneHour;

    // Getters and setters
}