package com.zjyun.b_spring快速入门.service.impl;

import com.zjyun.b_spring快速入门.service.MyService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@Service
public class MyServiceImpl implements MyService, ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("🧐Spring容器："+applicationContext);
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("ROOT容器中的bean🥔"+definitionName);
        }
    }
}
