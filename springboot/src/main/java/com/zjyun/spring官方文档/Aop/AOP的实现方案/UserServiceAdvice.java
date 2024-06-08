package com.zjyun.spring官方文档.Aop.AOP的实现方案;

import org.springframework.stereotype.Component;

/**
 * 增强类，内部提供增强方法
 *
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/8
 */
@Component
public class UserServiceAdvice {

    public void before() {
        System.out.println("before 增强！！");
    }

    public void after() {
        System.out.println("after 增强！！");
    }
}
