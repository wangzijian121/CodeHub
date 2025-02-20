package com.zjyun.spring_ioc.c_bean的实例化.c_通过实例工厂;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/31
 */

public class Bean1 {

    private String name;

    public Bean1() {
    }

    public Bean1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "name='" + name + '\'' +
                '}';
    }
}
