package com.zjyun.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjyun.springboot.entity.Order;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/9/3
 */
public interface ITask {
    void doOrderTask(int dataCount,IService<Order> ordersService, CountDownLatch countDownLatch);
}
