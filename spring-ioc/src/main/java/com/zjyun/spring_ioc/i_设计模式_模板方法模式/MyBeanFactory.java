package com.zjyun.spring_ioc.i_设计模式_模板方法模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/27
 */
public class MyBeanFactory {

    private List<BeanPostProcessor> list = new ArrayList<>();

    public Object getBean() {
        Object object = new Object();
        System.out.println("构造：" + object);
        System.out.println("依赖注入：" + object);

        System.out.println("初始化：" + object);
        for (BeanPostProcessor beanPostProcessor : list) {
            beanPostProcessor.inject();
        }
        return object;
    }

    public void addBeanPostProcess(BeanPostProcessor beanPostProcessor) {
        list.add(beanPostProcessor);
    }
}
