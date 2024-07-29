package com.zjyun.springboot.controller;

import com.zjyun.springboot.entity.Account;
import com.zjyun.springboot.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @author 王子健
 * @since 2024-07-26
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private IAccountService accountService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Integer id) {
        return accountService.getById(id);
    }


    @GetMapping("/log")
    public void  getLog() {
        logger.debug("wangzijian-debug");
        logger.info("wangzijian-info");
        logger.warn("wangzijian-warn");
        logger.error("wangzijian-error");
    }
}
