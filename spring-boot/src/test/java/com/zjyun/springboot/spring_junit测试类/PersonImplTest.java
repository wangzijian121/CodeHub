package com.zjyun.springboot.spring_junit测试类;

import com.zjyun.springboot.springboot集成mybatis.AccountMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonImplTest {

    /*    @Resource(type = PersonImpl.class)
        private IPerson person;*/
    @Autowired
    private IPerson person;

    @Autowired
    AccountMapper accountMapper;

    @Test
    public void say() {
        //System.out.println(person.say());
        Assert.assertEquals("wangzijian", person.say());
    }

    @Test
    public void getAccount() {
        System.out.println(accountMapper.selectAccountById(1));
    }

}