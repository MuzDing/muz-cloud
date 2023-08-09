package com.muz.cn.util;

public class FactorsUtils {
    public static double tempFactor(double temp) {
        double factor = 0.0;
        if (temp > 35) {
            factor = -0.1;
        } else if (temp > 20 && temp <= 35) {
            factor = 0.2;
        } else if (temp > 0 && temp <= 20) {
            factor = 0.1;
        } else if (temp > -10 && temp <= 0) {
            factor = -0.1;
        } else if (temp <= -10) {
            factor = -0.2;
        }
        return factor;
    }
    public static double WeatherFactor(double temp, double humidity) {
        double factor = 0.0;
        if (temp > 25) {
            factor = 0.0;
        } else if (temp > 20 && temp <= 25) {
            factor = 0.05;
        } else if (temp > 15 && temp <= 20) {
            factor = 0.1;
        } else if (temp > 10 && temp <= 15) {
        }
        return factor;
    }

    public static double totalFactor(double tempFactor, double weatherFactor) {
        return (tempFactor + weatherFactor)/ 2 + Math.min(tempFactor, weatherFactor);
    }

    // 开尔文温度转摄氏度
    public static double convertC(double k) {
        return k - 273.15;
    }





}
