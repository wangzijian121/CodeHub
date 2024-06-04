package com.zjyun.spring.Ioc解决的问题.impl;

import com.zjyun.spring.Ioc解决的问题.Dao;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
@Component
public class MyDao2 implements Dao {
    @Override
    public void select() {
        System.out.println("MyDao2 select");
    }
}
