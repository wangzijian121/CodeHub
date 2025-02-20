package com.zjyun.a_spring整合web;


import com.zjyun.a_spring整合web.model.Account;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
public interface AccountService {


    void transfer(Account Sender, Account recipient, long amount);

    Account getAccount(int id);
}
