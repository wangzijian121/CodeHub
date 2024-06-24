package com.zjyun.spring_ioc.bean的实例化.d_通过factoryBean接口;

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
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean的实例化/通过factorybean创建/service.xml");
        System.out.println(applicationContext.getBean("bean"));
    }
}
