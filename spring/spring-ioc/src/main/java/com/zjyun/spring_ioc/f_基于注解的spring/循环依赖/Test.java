package com.zjyun.spring_ioc.f_基于注解的spring.循环依赖;

import com.zjyun.spring_ioc.f_基于注解的spring.propertysource_profile注解.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: 循环依赖
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
