package com.zjyun.spring官方文档.注解;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/7
 */
public class MyAware implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("");
    }
}
