package com.zjyun.spring官方文档.注解;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
@Component
public class City {
    @Value("${spring.messages.encoding}")
    private String cityName;

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                '}';
    }
}
