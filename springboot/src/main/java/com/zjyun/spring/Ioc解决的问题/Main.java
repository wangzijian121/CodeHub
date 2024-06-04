package com.zjyun.spring.Ioc解决的问题;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(definitionName);
        }

    }
}
