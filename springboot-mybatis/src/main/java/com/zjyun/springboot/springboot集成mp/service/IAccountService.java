package com.zjyun.springboot.springboot集成mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjyun.springboot.springboot集成mp.entity.Account;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangzijian
 * @since 2024-07-25
 */
public interface IAccountService extends IService<Account> {
    void streamingQuery();
}
