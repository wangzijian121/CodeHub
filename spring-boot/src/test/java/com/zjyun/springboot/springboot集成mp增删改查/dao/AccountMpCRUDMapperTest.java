package com.zjyun.springboot.springboot集成mp增删改查.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjyun.springboot.springboot集成mp.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class AccountMpCRUDMapperTest {

    @Autowired
    AccountMpCRUDMapper accountMpCRUDMapper;

    /**
     * 【删除】账号通过ID 测试
     */
    @Test
    public void deleteAccountById() {
        accountMpCRUDMapper.deleteById(670);
    }

    /**
     * 【删除】账号通过对象 测试
     */
    @Test
    public void deleteAccountByEntity() {

        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setEntity(new Account(2, "BBB", 9000));
        accountMpCRUDMapper.delete(updateWrapper);
    }

    /**
     * 【删除】账号通过条件 测试
     */
    @Test
    public void deleteAccountByConditions() {
        accountMpCRUDMapper.delete(
                new LambdaQueryWrapper<Account>()
                        .gt(Account::getId, 600));
    }

    /**
     * 【增加】账号
     */
    @Test
    public void addAccountCRUDByMp() {
        accountMpCRUDMapper.insert(new Account(null, "wang", 1000));
    }

    /**
     * 【修改】账号-ID
     */
    @Test
    public void updateAccountById() {
        accountMpCRUDMapper.updateById(new Account(672, "wangzijian", 888));
    }

    /**
     * 【修改】账号-条件
     */
    @Test
    public void updateAccountByConditions() {

        Account account = new Account();
        account.setBalance(2000);
        accountMpCRUDMapper.update(account,
                new LambdaQueryWrapper<Account>()
                        .lt(Account::getId, 600));
    }

    /**
     * 【查询】通过ID
     */
    @Test
    public void getAccountById() {
        accountMpCRUDMapper.selectById(672);
    }

    /**
     * 【查询】查询单个one,限制单条返回
     */
    @Test
    public void getAccountOne() {
        //accountMpCRUDMapper.selectOne(new LambdaQueryWrapper<Account>().gt(Account::getBalance, 1000));
    }

    /**
     * 【查询】根据账号列表查询多条结果
     */
    @Test
    public void getAccountByIds() {

        accountMpCRUDMapper.selectBatchIds(
                Stream.of(2, 3, 4, 5).collect(Collectors.toList())
        );
    }

    /**
     * 【查询】根据条件查询
     */
    @Test
    public void getAccountByConditions() {
        accountMpCRUDMapper.selectList(new LambdaQueryWrapper<Account>().gt(Account::getBalance, 1000));
    }

    /**
     * 【查询】查询后分页
     */
    @Test
    public void getAccountPage() {
        //查询第1页,每页显示3条记录
        IPage accountPage = accountMpCRUDMapper.selectPage(new Page<>(1, 3), null);
        System.out.println(accountPage.toString());
        for (Object account : accountPage.getRecords()) {
            System.out.println("account: " + account);
        }
    }
}