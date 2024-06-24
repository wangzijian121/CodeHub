package com.zjyun.spring_ioc.生命周期回调;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
public class MyServiceUsedAnnotation {
    @PostConstruct
    public void destroy() throws Exception {
        System.out.println("@PostConstruct 服务销毁！");
    }

    @PreDestroy
    public void afterPropertiesSet() throws Exception {
        System.out.println("@PreDestroy 初始化！");
    }
}
