package com.muz.cn.pojo.bo.Weather;

import lombok.Data;
@Data
public class Weather {

    // 天气状况 ID
    private int id;
    // 天气参数组（雨、雪、云等）
    private String main;
    // 团体内的天气状况
    private String description;
    // 天气图标
    private String icon;

    // Getters and setters
}