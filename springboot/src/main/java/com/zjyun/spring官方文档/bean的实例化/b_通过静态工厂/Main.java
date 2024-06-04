package com.zjyun.spring官方文档.bean的实例化.b_通过静态工厂;

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
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean的实例化/通过静态工厂/service.xml");
        System.out.println(applicationContext.getBean("bean"));
    }
}
