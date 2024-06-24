package com.zjyun.spring_ioc.model;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/22
 */
public class Bean2 {

    private String beanName;

    public Bean2() {
        System.out.println("构造函数 bean2");
    }

    public Bean2(String beanName) {
        System.out.println("构造函数 bean2");
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String toString() {
        return "Bean2{" +
                "beanName='" + beanName + '\'' +
                '}';
    }
}
