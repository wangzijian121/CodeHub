package com.zjyun.spring.自定义后处理器.bean;

import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/27
 */
@Component
public class Bean2 {
    private String name;

    public Bean2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
