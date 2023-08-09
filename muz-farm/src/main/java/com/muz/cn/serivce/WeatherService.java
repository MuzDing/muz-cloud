package com.muz.cn.serivce;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muz.cn.pojo.bo.Weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * openweathermap 天气查询 一天1000次
 */
@Service
public class WeatherService {

    private String API_KEY = "0582e2ef79d4da44adde004c27577fe0";
    private final String IPINFO_API_URL = "https://ipinfo.io";
    private String API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private final long EXPIRATION_TIME_SECONDS = 12 * 60 * 60; // 12 hours

    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public WeatherResponse getWeatherByIp(String ipAddress) {
        String ipInfoUrl = IPINFO_API_URL + "/" + ipAddress + "/json";
        ResponseEntity<String> ipInfoResponse = restTemplate.getForEntity(ipInfoUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode ipInfoJson = objectMapper.readTree(ipInfoResponse.getBody());
            String city = ipInfoJson.get("city").asText();
            String country = ipInfoJson.get("country").asText();
            Object o = redisTemplate.opsForValue().get(city + "Weather");
            if (o != null) {
                return objectMapper.convertValue(o, WeatherResponse.class);
            }else{
                String openWeatherUrl = API_URL + "?q=" + city + "," + country + "&appid=" + API_KEY;
                ResponseEntity<WeatherResponse> weatherResponse = restTemplate.getForEntity(openWeatherUrl, WeatherResponse.class);
                redisTemplate.opsForValue().set(weatherResponse.getBody().getName()+"Weather", weatherResponse.getBody(),EXPIRATION_TIME_SECONDS,TimeUnit.SECONDS);
                return  weatherResponse.getBody();

            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

