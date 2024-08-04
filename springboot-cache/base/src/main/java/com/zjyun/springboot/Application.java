package com.zjyun.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Wang Zijian
 */
@SpringBootApplication
@MapperScan("com.zjyun.springboot.mapper")
@EnableCaching
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("🥔"+beanDefinitionName);
        }
    }
}