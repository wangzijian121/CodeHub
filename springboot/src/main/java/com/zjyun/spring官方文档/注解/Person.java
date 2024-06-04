package com.zjyun.spring官方文档.注解;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Component
@Data
@Service
public class Person {

    private City city;

    @Autowired
    public Person(City city) {
        this.city = city;
    }

    /*    @Autowired
    public void setCity(City city) {
        this.city = city;
    }*/
}