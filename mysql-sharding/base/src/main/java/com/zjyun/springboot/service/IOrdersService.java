package com.zjyun.springboot.service;

import com.zjyun.springboot.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.concurrent.ExecutionException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangzijian
 * @since 2024-09-02
 */
public interface IOrdersService extends IService<Order> {

    void generateAndInsert(int count);

    void  generateAndConcurrentInsert(int count) throws ExecutionException, InterruptedException;
}
