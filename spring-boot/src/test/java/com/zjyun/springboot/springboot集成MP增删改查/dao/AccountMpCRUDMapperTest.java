package com.zjyun.springboot.springboot集成MP增删改查.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountMpCRUDMapperTest {

    @Autowired
    private AccountMpCRUDMapper accountMpCRUDMapper;

    @Test
    public void select() {
        accountMpCRUDMapper.selectById(1);
    }
}