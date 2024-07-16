package com.zjyun.springboot.spring_junit测试类;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/7/15
 */
@Service
public class PersonImpl implements IPerson {

    @Override
    public String say() {
        return "wangzijian";
    }
}
