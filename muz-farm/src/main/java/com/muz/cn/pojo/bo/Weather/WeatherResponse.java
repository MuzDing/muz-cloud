package com.muz.cn.pojo.bo.Weather;

import lombok.Data;

/**
 * openweather 官网
 */
@Data
public class WeatherResponse {
    private Coord coord;
    private Weather[] weather;
    private String base;
    private Main main;
    // 能见度，米。能见度最大值为10公里
    private int visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    // 数据计算时间，unix，UTC
    private long dt;
    private Sys sys;
    // 与 UTC 的秒数变化
    private int timezone;
    // 城市编码id
    private long id;
    // 城市名
    private String name;
    // 内部编码
    private int cod;

}






