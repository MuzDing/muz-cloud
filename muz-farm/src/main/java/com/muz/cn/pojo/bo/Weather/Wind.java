package com.muz.cn.pojo.bo.Weather;

import lombok.Data;
@Data
public class Wind {
    // 风速。单位默认：米/秒，公制：米/秒，英制：英里/小时
    private double speed;
    // 风向，度（气象）
    private int deg;
    // 阵风。单位默认：米/秒，公制：米/秒，英制：英里/小时
    private double gust;

}