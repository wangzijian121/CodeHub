package com.zjyun.springboot.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrdersServiceImplTest {

    @Autowired
    private OrdersServiceImpl ordersService;

    @Test
    public void generateAndInsert() {
        System.out.println("顺序写入测试：");
        ordersService.generateAndInsert(100);
    }

    @Test
    public void generateAndConcurrentInsert() throws InterruptedException {
        System.out.println("并发写入测试,并发度：4");
        ordersService.generateAndConcurrentInsert(10000000);
    }
}