package com.zjyun.spring整合web.事务控制_整合mybatis_web.listener;

import com.zjyun.spring整合web.事务控制_整合mybatis_web.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
//@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("执行ServletContextListener 的初始化方法contextInitialized()...");
        ServletContext servletContext = sce.getServletContext();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        servletContext.setAttribute("applicationContext", context);
    }
}
