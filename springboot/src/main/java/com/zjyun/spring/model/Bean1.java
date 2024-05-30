package com.zjyun.spring.model;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/22
 */
public class Bean1 {

    @Autowired
    private Bean2 bean2;

    private String beanName;

    public Bean1() {
        System.out.println("构造函数 bean1");
    }

    public Bean1(Bean2 bean2, String beanName) {
        System.out.println("构造函数 bean1");
        this.bean2 = bean2;
        this.beanName = beanName;
    }

    public Bean2 getBean2() {
        return bean2;
    }

    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
