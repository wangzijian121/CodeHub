package com.zjyun.spring_ioc.依赖项.手动注入.依赖Set注入;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
public class City {
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
