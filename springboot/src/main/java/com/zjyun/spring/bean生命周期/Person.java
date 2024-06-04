package com.zjyun.spring.bean生命周期;

import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
@Component

public class Person implements BeanNameAware {
    private String name;
    private City city;

    @PostConstruct
    public void init() {
        System.out.println("初始化！ "+LocalTime.now());
    }

    public Person() {
    }

    public Person(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware#setBeanName "+LocalTime.now());
    }
}
