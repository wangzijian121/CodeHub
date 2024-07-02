package com.zjyun.spring_ioc.c_bean的实例化.a_通过构造函数;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/31
 */
public class Main {
    public static void main(String[] args) {

        /**
         * 通过构造函数实例化
         */
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean的实例化/通过构造函数/service.xml");
        System.out.println(applicationContext.getBean("bean"));
    }
}
