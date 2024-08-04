package com.zjyun.springboot.controller;

import com.zjyun.springboot.entity.Account;
import com.zjyun.springboot.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AccountController {

    //private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private IAccountService accountService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Integer id) {
        return accountService.getById(id);
    }


    @GetMapping("/log")
    public void  getLog() {
        log.debug("wangzijian-debug");
        log.info("wangzijian-info");
        log.warn("wangzijian-warn");
        log.error("wangzijian-error");
    }
}
