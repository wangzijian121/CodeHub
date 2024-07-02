package com.zjyun.spring_ioc.f_基于注解的spring.监听器_事件;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("R接收自定义事件 - " + event.getMessage());
    }
}
