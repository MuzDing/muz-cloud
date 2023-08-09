package com.muz.cn.pojo.baseEnum;

public enum WeatherConditionEnum {

    THUNDERSTORM_WITH_LIGHT_RAIN(200, "Thunderstorm",0 ,0,"thunderstorm with light rain", "11d", "11n"),
    THUNDERSTORM_WITH_RAIN(201, "Thunderstorm",0,0, "thunderstorm with rain", "11d", "11n"),
    THUNDERSTORM_WITH_HEAVY_RAIN(202, "Thunderstorm", 0,0,"thunderstorm with heavy rain", "11d", "11n"),
    LIGHT_THUNDERSTORM(210, "Thunderstorm",0,0, "light thunderstorm", "11d", "11n"),
    THUNDERSTORM(211, "Thunderstorm",0,0, "thunderstorm", "11d", "11n"),
    HEAVY_THUNDERSTORM(212, "Thunderstorm",0,0, "heavy thunderstorm", "11d", "11n"),
    RAGGED_THUNDERSTORM(221, "Thunderstorm",0,0, "ragged thunderstorm", "11d", "11n"),
    THUNDERSTORM_WITH_LIGHT_DRIZZLE(230, "Thunderstorm",0,0, "thunderstorm with light drizzle", "11d", "11n"),
    THUNDERSTORM_WITH_DRIZZLE(231, "Thunderstorm",0,0, "thunderstorm with drizzle", "11d", "11n"),
    THUNDERSTORM_WITH_HEAVY_DRIZZLE(232, "Thunderstorm",0,0, "thunderstorm with heavy drizzle", "11d", "11n"),
    LIGHT_INTENSITY_DRIZZLE(300, "Drizzle",0.1,0.1, "light intensity drizzle", "09d", "09n"),
    DRIZZLE(301, "Drizzle", 0.1,0.1,"drizzle", "09d", "09n"),
    HEAVY_INTENSITY_DRIZZLE(302, "Drizzle", 0.1,0.1,"heavy intensity drizzle", "09d", "09n"),
    LIGHT_INTENSITY_DRIZZLE_RAIN(310, "Drizzle", 0.1,0.1,"light intensity drizzle rain", "09d", "09n"),
    DRIZZLE_RAIN(311, "Drizzle", 0.1,0.1,"drizzle rain", "09d", "09n"),
    HEAVY_INTENSITY_DRIZZLE_RAIN(312, "Drizzle", 0.1,0.1,"heavy intensity drizzle rain", "09d", "09n"),
    SHOWER_RAIN_AND_DRIZZLE(313, "Drizzle",0.1,0.1, "shower rain and drizzle", "09d", "09n"),
    HEAVY_SHOWER_RAIN_AND_DRIZZLE(314, "Drizzle", 0.1,0.1,"heavy shower rain and drizzle", "09d", "09n"),
    SHOWER_DRIZZLE(321, "Drizzle", 0.1,0.1,"shower drizzle", "09d", "09n"),
    LIGHT_RAIN(500, "Rain", 0.2,0.2,"light rain", "10d", "10n"),
    MODERATE_RAIN(501, "Rain",0.2,0.2,"moderate rain", "10d", "10n"),
    HEAVY_INTENSITY_RAIN(502, "Rain",0.2,0.2, "heavy intensity rain", "10d", "10n"),
    VERY_HEAVY_RAIN(503, "Rain", -0.1,-0.1,"very heavy rain", "10d", "10n"),
    EXTREME_RAIN(504, "Rain", -0.2,-0.2,"extreme rain", "10d", "10n"),
    FREEZING_RAIN(511, "Rain", -0.1,-0.1,"freezing rain", "13d", "13n"),
    LIGHT_INTENSITY_SHOWER_RAIN(520, "Rain", -0.1,-0.1,"light intensity shower rain", "09d", "09n"),
    SHOWER_RAIN(521, "Rain", -0.1,-0.1,"shower rain", "09d", "09n"),
    HEAVY_INTENSITY_SHOWER_RAIN(522, "Rain", -0.1,-0.1,"heavy intensity shower rain", "09d", "09n"),
    RAGGED_SHOWER_RAIN(531, "Rain", -0.1,-0.1,"ragged shower rain", "09d", "09n"),
    LIGHT_SNOW(600, "Snow", 0.1,0.1,"light snow", "13d", "13n"),
    SNOW(601, "Snow", 0.1,0.1,"snow", "13d", "13n"),
    HEAVY_SNOW(602, "Snow", -0.2,-0.2,"heavy snow", "13d", "13n"),
    SLEET(611, "Snow", -0.1,-0.1,"sleet", "13d", "13n"),
    LIGHT_SHOWER_SLEET(612, "Snow", 0.1,0.1,"light shower sleet", "13d", "13n"),
    SHOWER_SLEET(613, "Snow",0.1,0.1, "shower sleet", "13d", "13n"),
    LIGHT_RAIN_AND_SNOW(615, "Snow", 0.1,0.1,"light rain and snow", "13d", "13n"),
    RAIN_AND_SNOW(616, "Snow", 0.2,0.2,"rain and snow", "13d", "13n"),
    LIGHT_SHOWER_SNOW(620, "Snow", -0.1,-0.1,"light shower snow", "13d", "13n"),
    SHOWER_SNOW(621, "Snow", -0.1,-0.1,"shower snow", "13d", "13n"),
    HEAVY_SHOWER_SNOW(622, "Snow", -0.1,-0.1,"heavy shower snow", "13d", "13n"),
    MIST(701, "Atmosphere", 0,0,"mist", "50d", "50n"),
    SMOKE(711, "Atmosphere",0,0,"smoke", "50d", "50n"),
    HAZE(721, "Atmosphere", 0,0,"haze", "50d", "50n"),
    DUST_OR_SAND(731, "Atmosphere", 0,0,"dust or sand", "50d", "50n"),
    FOG(741, "Atmosphere",0,0, "fog", "50d", "50n"),
    SAND(751, "Atmosphere", 0,0,"sand", "50d", "50n"),
    DUST(761, "Atmosphere", 0,0,"dust", "50d", "50n"),
    VOLCANIC_ASH(762, "Atmosphere",-0.3,-0.3, "volcanic ash", "50d", "50n"),
    SQUALLS(771, "Atmosphere",-0.3,-0.3, "squalls", "50d", "50n"),
    TORNADO(781, "Atmosphere", -0.3,-0.3,"tornado", "50d", "50n"),
    CLEAR_SKY(800, "Clear Sky", 0,0,"clear sky", "01d", "01n"),
    FEW_CLOUDS(801, "Clouds", 0,0,"few clouds", "02d", "02n"),
    SCATTERED_CLOUDS(802, "Clouds",0,0, "scattered clouds", "03d", "03n"),
    BROKEN_CLOUDS(803, "Clouds",0,0, "broken clouds", "04d", "04n"),
    OVERCAST_CLOUDS(804, "Clouds",0,0, "overcast clouds", "04d", "04n");

    private Integer id;
    private String main;
    private String description;
    private double maturityFactors;
    private double yieldFactor;
    private String iconDay;
    private String iconNight;

    public Integer getId(){
        return id;
    }
    public String getMain() {
        return main;
    }
    public String getDescription() {
        return description;
    }
    public double getMaturityFactors() {
        return maturityFactors;
    }
    public double getYieldFactor() {
        return yieldFactor;
    }
    public String getIconDay() {
        return iconDay;
    }
    public String getIconNight() {
        return iconNight;
    }

    WeatherConditionEnum(int id, String main, double maturityFactors,double yieldFactor, String description, String iconDay, String iconNight) {
        this.id = id;
        this.main = main;
        this.maturityFactors = maturityFactors;
        this.yieldFactor = yieldFactor;
        this.description = description;
        this.iconDay = iconDay;
        this.iconNight = iconNight;
    }

    public static WeatherConditionEnum getWeatherConditionEnumById(Integer id) {
        for (WeatherConditionEnum ele : WeatherConditionEnum.values()) {
            if (ele.getId().equals(id)) {
                return ele;
            }
        }
        return null;
    }


}

