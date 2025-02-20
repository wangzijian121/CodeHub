package com.zjyun.a_spring整合web.listener;

import com.zjyun.a_spring整合web.config.AppConfig;
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
