package com.zjyun.springboot.service.impl;

import com.zjyun.springboot.entity.Order;
import com.zjyun.springboot.mapper.OrdersMapper;
import com.zjyun.springboot.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjyun.springboot.service.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Order> implements IOrdersService {

    private final static Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

    private final ITask task;

    @Autowired
    public OrdersServiceImpl(ITask task) {
        this.task = task;
    }

    @Override
    public void generateAndInsert(int count) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Order order = generateRandomOrder();
            orders.add(order);
        }
        long start = System.currentTimeMillis();
        this.saveBatch(orders, 10000);
        long end = (System.currentTimeMillis() - start) / 1000;
        System.out.println("用时" + end + "秒");
    }

    @Override
    public void generateAndConcurrentInsert(int dataCount) throws InterruptedException {
        final int threadCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        long start = System.currentTimeMillis();
        IntStream.range(0, threadCount).forEach(x -> task.doOrderTask(dataCount / threadCount, this, countDownLatch));
        countDownLatch.await();
        long end = (System.currentTimeMillis() - start) / 1000;
        logger.info("{}OK✅用时：{}秒", Thread.currentThread().getName(), end);
    }
}
