package com.zjyun.spring.自定义后处理器.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/27
 */
@Component
public class Bean1 {
    @Autowired
    private Bean2 bean2;
    private String name;

    public String getName() {
        return name;
    }


    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", name='" + name + '\'' +
                '}';
    }
}