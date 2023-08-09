package com.muz.cn.pojo.bo.Weather;

import lombok.Data;

@Data
public class Sys {
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
