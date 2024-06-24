package com.zjyun.spring整合web.事务控制_整合mybatis_web.config;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
public class AnnotationConfigWebApplicationContextConfig  extends AnnotationConfigWebApplicationContext {
    public AnnotationConfigWebApplicationContextConfig() {
        this.register(AppConfig.class);
    }
}
