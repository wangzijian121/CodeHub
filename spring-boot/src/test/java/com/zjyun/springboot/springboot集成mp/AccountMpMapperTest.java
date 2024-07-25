package com.zjyun.springboot.springboot集成mp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
@SpringBootTest
public class AccountMpMapperTest {

    private  AccountMpMapper accountMpMapper;

    @Autowired
    public AccountMpMapperTest(AccountMpMapper accountMpMapper) {
        this.accountMpMapper = accountMpMapper;
    }

    @Test
    public void testSelect() {
        accountMpMapper.selectById(3);
    }
}