package com.zjyun.springboot.service.impl;

import com.zjyun.springboot.entity.Order;
import com.zjyun.springboot.mapper.OrdersMapper;
import com.zjyun.springboot.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Order> implements IOrdersService {


    @Override
    public void generate(int count) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Order order = generateRandomOrder();
            orders.add(order);
        }
        long start = System.currentTimeMillis();
        this.saveBatch(orders,10000);
        long end = (System.currentTimeMillis()-start)/1000;
        System.out.println("用时"+end+"秒");
    }

}
