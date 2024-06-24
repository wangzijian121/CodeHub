package com.zjyun.spring_ioc.Ioc解决的问题;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
@Service
public class MyService {
    private Dao dao;

    @Autowired
    public MyService(@Qualifier("myDao1") Dao dao) {
        this.dao = dao;
    }
}
