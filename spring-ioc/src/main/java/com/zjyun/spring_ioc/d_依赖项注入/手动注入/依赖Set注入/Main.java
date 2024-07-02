package com.zjyun.spring_ioc.d_依赖项注入.手动注入.依赖Set注入;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("依赖项/手动注入/依赖Set注入/applicationContext.xml");
        applicationContext.refresh();
        System.out.println("依赖Set注入: "+applicationContext.getBean("person"));
    }
}
