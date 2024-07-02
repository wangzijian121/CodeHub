package com.zjyun.spring_ioc.c_bean的实例化.b_通过静态工厂;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/31
 */
public class BeanFactory {
    public BeanFactory() {
    }

    public static Bean createInstance() {
        return  new Bean();
    }
}
