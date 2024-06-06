package com.zjyun.spring官方文档.注解;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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