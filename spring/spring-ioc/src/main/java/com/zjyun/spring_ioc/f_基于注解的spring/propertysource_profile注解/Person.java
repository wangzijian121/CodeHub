package com.zjyun.spring_ioc.f_基于注解的spring.propertysource_profile注解;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Person {

    private String name;

    public Person(@Value("${app.name}") String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", name='" + name + '\'' +
                '}';
    }
}