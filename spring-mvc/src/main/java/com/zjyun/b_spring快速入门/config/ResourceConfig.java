package com.zjyun.b_spring快速入门.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/25
 */
@Configuration
@EnableWebMvc
public class ResourceConfig {
    @Bean
    public DefaultServletHttpRequestHandler defaultServletHttpRequestHandler() {
        System.out.println("初始化defaultServletHttpRequestHandler Bean");
        DefaultServletHttpRequestHandler defaultServletHttpRequestHandler = new DefaultServletHttpRequestHandler();
        return defaultServletHttpRequestHandler;
    }
}
