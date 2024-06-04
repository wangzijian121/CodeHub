package com.zjyun.spring官方文档.依赖项.自动注入;

import com.zjyun.spring.自定义后处理器.自定义BeanFactoryPostProcessor后处理器.MyBeanFactoryPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("依赖项/自动注入/applicationContext.xml");
        applicationContext.refresh();

        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(definitionName);
        }
        System.out.println("自动注入-通过字段: " + applicationContext.getBean("person"));
    }
}

class City {

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

class Person {

    @NotNull
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