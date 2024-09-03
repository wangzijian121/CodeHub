package com.zjyun.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjyun.springboot.entity.Order;
import com.zjyun.springboot.mapper.OrdersMapper;
import com.zjyun.springboot.service.IBaseService;
import com.zjyun.springboot.service.IOrdersService;
import com.zjyun.springboot.service.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
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
public class TaskServiceImpl implements ITask {

    private final static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Async("taskExecutor")
    public void doOrderTask(int dataCount, IService<Order> ordersService, CountDownLatch countDownLatch) {
        logger.info("{} 执行中...", Thread.currentThread().getName());
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < dataCount; i++) {
            Order order = generateRandomOrder();
            orders.add(order);
        }
        ordersService.saveBatch(orders, 10000);
        countDownLatch.countDown();
    }
}
