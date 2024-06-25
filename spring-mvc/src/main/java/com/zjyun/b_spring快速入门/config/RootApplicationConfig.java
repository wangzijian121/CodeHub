package com.zjyun.b_spring快速入门.config;

import org.springframework.context.annotation.*;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Configuration
@ComponentScan("com.zjyun.b_spring快速入门.service")
public class RootApplicationConfig {
    @Bean("multipartResolver")
    public MultipartResolver addMultipartResolver() {
        System.out.println("初始化CommonsMultipartResolver");
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSizePerFile(5242880);//每个文件的限制大小
        commonsMultipartResolver.setMaxUploadSize(5242880);//上传文件的总大小
        commonsMultipartResolver.setMaxInMemorySize(52428800);//上传文件的缓存大小
        return commonsMultipartResolver;
    }
}
