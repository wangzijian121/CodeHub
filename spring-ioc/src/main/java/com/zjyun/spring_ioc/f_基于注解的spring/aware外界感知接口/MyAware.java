package com.zjyun.spring_ioc.f_基于注解的spring.aware外界感知接口;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/7
 */
@Component
public class MyAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("bean 的生命周期中实例化之后，初始化之前，执行了setApplicationContext感知方法！");
        System.out.println("看我获取applicationContext容器的信息："+applicationContext);
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("获取到容器内的bean🥔"+beanDefinitionName);
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
