package com.zjyun.spring_ioc.f_基于注解的spring.propertysource_profile注解;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.zjyun.spring_ioc.f_基于注解的spring")
@PropertySource(value = {"classpath:application-${app.env:prod}.properties"})
public class AppConfig {

    @Profile("dev")
    @Bean
    public Object initDev(@Value("${app.name}") String name) {
        System.out.println("（需要指定spring.profiles.active=dev才添加到容器中）initDev:"+name);
        return null;
    }
    @Profile("prod")
    @Bean
    public Object initProd(@Value("${app.name}") String name) {
        System.out.println("（需要指定spring.profiles.active=prod才添加到容器中）initProd:"+name);
        return null;
    }
    @Bean
    public Object initDefault() {
        System.out.println("initDefault 默认执行的方法，添加到容器中！");
        return null;
    }
}

