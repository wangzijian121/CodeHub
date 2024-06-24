package com.zjyun.spring_ioc.生命周期回调;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
@Component
public class MyService implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("服务销毁！");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化！");
    }
}
