package com.zjyun.spring官方文档.容器的使用;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/31
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("容器的使用/service1.xml");
        System.out.println(context.getBean("bean1"));
        System.out.println(context.getBean("bean2"));
    }
}
