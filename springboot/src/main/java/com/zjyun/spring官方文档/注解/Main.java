package com.zjyun.spring官方文档.注解;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/4
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfig.class);
        applicationContext.refresh();
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(definitionName);
        }
        System.out.println(applicationContext.getBean("person"));
    }
}
