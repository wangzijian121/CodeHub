package com.zjyun.spring_ioc.bean的实例化.c_通过实例工厂;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/31
 */
public class BeanFactory {

    public BeanFactory() {
    }

    public  Bean1 createBean1Instance() {
        return new Bean1();
    }

    public  Bean2 createBean2Instance() {
        return new Bean2();
    }
}
