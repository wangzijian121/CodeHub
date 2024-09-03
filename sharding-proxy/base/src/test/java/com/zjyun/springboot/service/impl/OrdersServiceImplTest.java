package com.zjyun.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class OrdersServiceImplTest {

    @Autowired
    private OrdersServiceImpl ordersService;

    @Test
    public void generateAndInsert() {
        System.out.println("顺序写入测试：");
        ordersService.generateAndInsert(2000000);
    }

    @Test
    public void generateAndConcurrentInsert() throws InterruptedException {
        System.out.println("并发写入测试,并发度：3");
        ordersService.generateAndConcurrentInsert(2000000);
    }
}