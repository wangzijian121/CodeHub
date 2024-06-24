package com.zjyun.spring整合web.事务控制_整合mybatis_web;

import com.zjyun.spring整合web.事务控制_整合mybatis_web.model.Account;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
public interface AccountService {


    void transfer(Account Sender, Account recipient, long amount);

    Account getAccount(int id);
}
