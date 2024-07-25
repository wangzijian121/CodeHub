package com.zjyun.springboot.springboot集成mp.service.impl;

import com.zjyun.springboot.springboot集成mp.entity.Account;
import com.zjyun.springboot.springboot集成mp.mapper.AccountMapper;
import com.zjyun.springboot.springboot集成mp.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangzijian
 * @since 2024-07-25
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
