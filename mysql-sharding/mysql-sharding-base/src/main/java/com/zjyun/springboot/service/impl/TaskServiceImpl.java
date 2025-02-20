package com.zjyun.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjyun.springboot.entity.Order;
import com.zjyun.springboot.service.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static com.zjyun.springboot.util.OrderGenerator.generateRandomOrder;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangzijian
 * @since 2024-09-02
 */
@Service
public class TaskServiceImpl implements ITask {

    private final static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Async("taskExecutor")
    @Transactional
    public void doOrderTask(int dataCount, IService<Order> ordersService, CountDownLatch countDownLatch) {
        logger.info("{} 执行中...", Thread.currentThread().getName());
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < dataCount; i++) {
            Order order = generateRandomOrder();
            orders.add(order);
        }
        System.out.println("生成完成！");
        ordersService.saveBatch(orders, 5000);
        countDownLatch.countDown();
    }
}
