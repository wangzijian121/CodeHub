package com.zjyun.spring_ioc.基于XML的Spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/30
 */
public class ApplicationTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        MyService service1 =(MyService) context.getBean("MyServiceImpl");
        MyService service2 =(MyService) context.getBean("MyServiceImpl");
        MyService service3 =(MyService) context.getBean("MyServiceImpl");
        System.out.println(service1);
        System.out.println(service2);
        System.out.println(service3);
        //调用销毁方法
        context.close();
    }
}
