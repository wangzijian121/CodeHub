package com.zjyun.spring_ioc.bean的实例化.c_通过实例工厂;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/31
 */

public class Bean2 {

    private String name;

    public Bean2() {
    }

    public Bean2(String name) {
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
