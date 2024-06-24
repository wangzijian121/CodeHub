package com.zjyun.b_spring快速入门.config;

import org.springframework.context.annotation.*;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Configuration
@ComponentScan("com.zjyun.b_spring快速入门.service")
public class RootApplicationConfig {

}
