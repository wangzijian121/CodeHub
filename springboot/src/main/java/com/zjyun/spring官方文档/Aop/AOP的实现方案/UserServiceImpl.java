package com.zjyun.spring官方文档.Aop.AOP的实现方案;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 用户服务类（待增强）
 *
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/8
 */
@Service
public class UserServiceImpl implements IService {

    public void s1() {
        System.out.println("s1....");
    }

    public void s2() {
        System.out.println("s2....");
    }
}
