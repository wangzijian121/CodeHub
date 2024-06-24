package com.zjyun.spring_ioc.bean生命周期;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalTime;
import java.util.Arrays;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(context.getBean("person"));
        Arrays.asList(context.getBeanDefinitionNames())
                .stream()
                .filter(x -> x.contains("person"))
                .forEach(x -> System.out.println(x + " "+LocalTime.now()));
    }
}
