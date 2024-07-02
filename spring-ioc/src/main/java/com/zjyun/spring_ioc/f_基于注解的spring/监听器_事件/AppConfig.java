package com.zjyun.spring_ioc.f_基于注解的spring.监听器_事件;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *监听器启动， 并通过publisher 发送事件，监听器接收事件并输出。
 */
@Configuration
@ComponentScan(basePackages = "com.zjyun.spring_ioc.f_基于注解的spring.监听器_事件")
public class AppConfig {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomEventPublisher publisher = context.getBean(CustomEventPublisher.class);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            publisher.publishEvent("💬您好，这是一个自定义事件" + (i + 1));
        }

        context.close();
    }
}
