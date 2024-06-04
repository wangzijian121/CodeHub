package com.zjyun.spring官方文档.生命周期回调;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        //context.refresh();
        //System.out.println(context.getBean("MyServiceUsedAnnotation"));
        for (String definitionName : context.getBeanDefinitionNames()) {
            System.out.println(definitionName);
        }
        context.close();
    }
}
