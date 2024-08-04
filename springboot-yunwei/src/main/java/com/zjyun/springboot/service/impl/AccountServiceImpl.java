package com.zjyun.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjyun.springboot.entity.Account;
import com.zjyun.springboot.mapper.AccountMapper;
import com.zjyun.springboot.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王子健
 * @since 2024-07-26
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
