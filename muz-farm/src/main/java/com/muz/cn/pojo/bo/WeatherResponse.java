package com.muz.cn.pojo.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Data
class Coord {
    private double lon;
    private double lat;

    // Getters and setters
}
@Data
class Weather {
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
@Data
class Main {
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
@Data
class Wind {
    // 风速。单位默认：米/秒，公制：米/秒，英制：英里/小时
    private double speed;
    // 风向，度（气象）
    private int deg;
    // 阵风。单位默认：米/秒，公制：米/秒，英制：英里/小时
    private double gust;

}
@Data
class Rain {
    // (如果有)过去 1 小时的雨量，毫米。请注意，该参数仅可使用 mm 作为测量单位
    @JsonProperty("1h")
    private double oneHour;

    // Getters and setters
}
@Data
class Clouds {
    // 云度 %
    private int all;

    // Getters and setters
}
@Data
class Sys {
    // 内部参数
    private int type;
    // 内部参数
    private int id;
    // 国家
    private String country;
    // 日出时间
    private long sunrise;
    // 日落时间
    private long sunset;

}
