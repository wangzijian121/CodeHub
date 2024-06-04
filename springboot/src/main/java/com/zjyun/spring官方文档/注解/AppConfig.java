package com.zjyun.spring官方文档.注解;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
@Configuration
@ComponentScan("com.zjyun.spring官方文档.注解")
@PropertySource("classpath:application.properties")
public class AppConfig {
 /*   @Bean
    @Primary
    public Person getPerson(){
        return new Person();
    }*/
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
