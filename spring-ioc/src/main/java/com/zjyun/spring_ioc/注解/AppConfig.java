package com.zjyun.spring_ioc.注解;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.zjyun.spring官方文档.注解")
@PropertySource(value = {"classpath:application-${env}.properties"})
public class AppConfig {

    @Profile("dev")
    @Bean
    public Object initDev(@Value("${app.name}") String name) {
        System.out.println("initDev:"+name);
        return null;
    }
    @Profile("dev")
    @Bean
    public Object initProd(@Value("${app.name}") String name) {
        System.out.println("initDev:"+name);
        return null;
    }
}

