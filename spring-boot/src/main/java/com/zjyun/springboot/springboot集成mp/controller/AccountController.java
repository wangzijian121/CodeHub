package com.zjyun.springboot.springboot集成mp.controller;

import com.zjyun.springboot.springboot集成mp.entity.Account;
import com.zjyun.springboot.springboot集成mp.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangzijian
 * @since 2024-07-25
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;


    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Integer id) {
        String status = null;
        Account account = accountService.getById(id);
        return account;
    }

    @GetMapping("/streamingQuery")
    public void streamingQuery() {
        accountService.streamingQuery();
    }
}
