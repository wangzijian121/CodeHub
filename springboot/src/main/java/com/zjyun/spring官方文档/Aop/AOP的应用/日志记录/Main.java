package com.zjyun.spring官方文档.Aop.AOP的应用.日志记录;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        for (String definitionName : context.getBeanDefinitionNames()) {
            System.out.println(definitionName);
        }
        MyService bean =context.getBean(MyService.class);
        bean.fun1();
        bean.fun2();

    }
}
