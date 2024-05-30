package com.zjyun.spring.基于XML的Spring.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/30
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;

    public void init() {
        System.out.println("初始化！");
    }

    public void destory() {
        System.out.println("销毁！");
    }
}
