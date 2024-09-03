package com.zjyun.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class OrdersServiceImplTest {

    @Autowired
    private OrdersServiceImpl ordersService;

    @Test
    public void generate() {
        System.out.println("测试");
        ordersService.generate(990000);
    }
}