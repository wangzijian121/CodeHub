package com.zjyun.springboot.controller;

import com.zjyun.springboot.entity.Account;
import com.zjyun.springboot.service.IAccountService;
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
 * @since 2024-07-30
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("{id}")
    public Account getAccount(@PathVariable Integer id) {
        return accountService.getById(id);
    }
}
