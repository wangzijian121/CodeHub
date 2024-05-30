package com.zjyun.spring.自定义后处理器.bean;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/27
 */
public class Bean3 {
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean3{" +
                "name='" + name + '\'' +
                '}';
    }
}
