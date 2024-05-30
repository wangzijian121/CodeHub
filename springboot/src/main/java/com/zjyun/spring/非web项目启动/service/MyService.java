package com.zjyun.spring.非web项目启动.service;

import com.zjyun.spring.非web项目启动.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/29
 */
@Service
public class MyService {

    private Person person;

    @Autowired
    public MyService(Person person) {
        this.person = person;
    }

    public void sayHello() {
        System.out.println(person.getName()+" hello!!");
    }
}
