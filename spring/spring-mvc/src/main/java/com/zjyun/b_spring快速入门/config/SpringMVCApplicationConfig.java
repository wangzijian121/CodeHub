package com.zjyun.b_spring快速入门.config;

import com.zjyun.b_spring快速入门.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Configuration
@ComponentScan({"com.zjyun.b_spring快速入门.controller","com.zjyun.b_spring快速入门.ex"})
//开启MVC注释
@EnableWebMvc //<mvc:annonation-driven>
public class SpringMVCApplicationConfig implements WebMvcConfigurer {

    /**
     * 替代： <mvc:default-servlet-handler>
     * <p>
     * 开启默认的Servlet处理器
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 替代：<mvc:interceptors>
     * <mvc:interceptor>
     * <mvc:mapping path="/**"/>
     * <bean class="com.zjyun.b_spring快速入门.interceptor.MyInterceptor"/>
     * <mvc:interceptors/>
     * <mvc:interceptors/>
     * <p>
     * 添加自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        MyInterceptor myInterceptor = new MyInterceptor();
        registry.addInterceptor(myInterceptor).addPathPatterns("/my");
    }


    /**
     * 异常处理
     * @return
     */
    /*@Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        exceptionResolver.setDefaultErrorView("index.html");
        Properties properties = new Properties();
        properties.setProperty("java.lang.ArithmeticException", "zero_ex.html");
        properties.setProperty("java.lang.Exception", "index.html");
        exceptionResolver.setExceptionMappings(properties);
        return exceptionResolver;
    }*/

    /**
     * 开启多部分解析程序(文件上传)
     *
     * @return
     */
    @Bean("multipartResolver")
    public MultipartResolver addMultipartResolver() {
        System.out.println("初始化CommonsMultipartResolver");
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        //每个文件的限制大小
        commonsMultipartResolver.setMaxUploadSizePerFile(5242880);
        //上传文件的总大小
        commonsMultipartResolver.setMaxUploadSize(5242880);
        //上传文件的缓存大小
        commonsMultipartResolver.setMaxInMemorySize(52428800);
        return commonsMultipartResolver;
    }

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
}
