package com.muz.cn.pojo.bo.Weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Main {
    // 温度. 开尔文
    private double temp;
    // 体感温度。开尔文
    @JsonProperty("feels_like")
    private double feelsLike;
    // 目前最低温度。这是目前观测到的最低温度
    @JsonProperty("temp_min")
    private double tempMin;
    // 当前最高温度。这是目前观测到的最高温度
    @JsonProperty("temp_max")
    private double tempMax;
    // 海平面大气压，hPa
    private int pressure;
    // 湿度 %
    private int humidity;
    // 海平面大气压，hPa
    @JsonProperty("sea_level")
    private int seaLevel;
    // 地平面大气压，hPa
    @JsonProperty("grnd_level")
    private int groundLevel;

}