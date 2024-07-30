package com.zjyun.springboot.service.impl;

import com.zjyun.springboot.entity.Account;
import com.zjyun.springboot.mapper.AccountMapper;
import com.zjyun.springboot.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangzijian
 * @since 2024-07-30
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
