package com.zjyun.b_spring快速入门.config;

import com.zjyun.b_spring快速入门.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Configuration
@ComponentScan("com.zjyun.b_spring快速入门.controller")
public class WebApplicationConfig {

    /**
     * 转换器：将请求过来的json转换为bean对象
     *
     * @return
     */
    @Bean
    public RequestMappingHandlerAdapter getAdapter() {
        System.out.println("执行getAdapter 转换器！");
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
        //指定转换器
        List<HttpMessageConverter<?>> collect = Stream.of(new MappingJackson2HttpMessageConverter()).collect(Collectors.toList());
        requestMappingHandlerAdapter.setMessageConverters(collect);
        return requestMappingHandlerAdapter;
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
}
