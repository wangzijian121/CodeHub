package com.zjyun.spring_ioc.注解;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/7
 */
public class Test {
    public static void main(String[] args) {
        //1.实例化容器对象
        AnnotationConfigApplicationContext applicationContext =new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println(applicationContext.getBean(X.class));
    }
}
