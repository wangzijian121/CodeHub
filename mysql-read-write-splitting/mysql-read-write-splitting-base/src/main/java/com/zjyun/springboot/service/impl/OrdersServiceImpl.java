package com.zjyun.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjyun.springboot.entity.Order;
import com.zjyun.springboot.mapper.OrdersMapper;
import com.zjyun.springboot.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjyun.springboot.service.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
//@Transactional //取消事务才会走从库
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Order> implements IOrdersService {

    private final static Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

    private final ITask task;

    private final OrdersMapper ordersMapper;

    @Autowired
    public OrdersServiceImpl(ITask task, OrdersMapper ordersMapper) {
        this.task = task;
        this.ordersMapper = ordersMapper;
    }

    @Override
    public void generateAndInsert(int count) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Order order = generateRandomOrder();
            orders.add(order);
        }
        long start = System.currentTimeMillis();
        System.out.println("生成完成！");
        this.saveBatch(orders, 1000);
        long end = System.currentTimeMillis() - start;
        System.out.println("✅用时" + end + "ms" + ",count：" + count);
    }

    @Override
    public void select() {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", "4821eec9-0768-4800-818f-7153a3c1fe2c");
        List<Order> orders = baseMapper.selectList(queryWrapper);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
