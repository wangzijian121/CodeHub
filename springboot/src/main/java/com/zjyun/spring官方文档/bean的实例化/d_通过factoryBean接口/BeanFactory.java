package com.zjyun.spring官方文档.bean的实例化.d_通过factoryBean接口;

import com.zjyun.spring官方文档.bean的实例化.c_通过实例工厂.Bean1;
import com.zjyun.spring官方文档.bean的实例化.c_通过实例工厂.Bean2;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/31
 */
@Configuration
public class BeanFactory implements FactoryBean<Bean> {

    public BeanFactory() {
    }

    @Override
    public Bean getObject() throws Exception {
        return new Bean();
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
