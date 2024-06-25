package com.zjyun.b_spring快速入门.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
public class SpringMvcAnnotationConfigWebApplicationContext extends AnnotationConfigWebApplicationContext {
    public SpringMvcAnnotationConfigWebApplicationContext() {
        this.register(SpringMvcApplicationConfig.class);
    }
}
