package com.zjyun.spring官方文档.依赖项.手动注入.依赖Set注入;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
public class Person {
    private String name;
    private City city;

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
}
