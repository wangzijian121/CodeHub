package com.zjyun.springboot.springboot集成mp.service.impl;


import com.zjyun.springboot.springboot集成mp.service.IAccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    private IAccountService accountService;
    @Test
    public void streamingQuery() {
        accountService.streamingQuery();
    }
}