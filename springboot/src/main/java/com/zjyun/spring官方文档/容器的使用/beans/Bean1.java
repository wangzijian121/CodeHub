package com.zjyun.spring官方文档.容器的使用.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
