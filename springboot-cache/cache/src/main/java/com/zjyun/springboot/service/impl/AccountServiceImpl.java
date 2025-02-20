package com.zjyun.springboot.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjyun.springboot.entity.Account;
import com.zjyun.springboot.entity.Person;
import com.zjyun.springboot.enums.Status;
import com.zjyun.springboot.mapper.AccountMapper;
import com.zjyun.springboot.service.IAccountService;
import com.zjyun.springboot.service.impl.base.BaseServiceImpl;
import com.zjyun.springboot.utils.PageInfo;
import com.zjyun.springboot.utils.Result;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类-使用SpringCache作为缓存
 * </p>
 *
 * @author wangzijian
 * @since 2024-07-30
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountMapper, Account> implements IAccountService {



    @Override
    @Cacheable(value = "cache1", key = "#pageNo+'-'+#pageSize")
    public Result queryAccountListPaging(Integer pageNo, Integer pageSize) {

        Result result = new Result();
        PageInfo<Account> pageInfo = new PageInfo<>(pageNo, pageSize);
        Page<Account> page = new Page<>(pageNo, pageSize);
        IPage<Account> accountPage = baseMapper.selectPage(page, null);
        List<Account> accountList = accountPage.getRecords();
        pageInfo.setTotal((int) accountPage.getTotal());
        pageInfo.setTotalList(accountList);
        result.setData(pageInfo);
        putMsg(result, Status.SUCCESS);
        return result;
    }

    @Override
    @CachePut(value = "cache2",key = "#account.id") // 在这里直接启用缓存，key是作为Redis缓存的key
    public Result saveAccount(Account account) {
        baseMapper.insert(account);
        return Result.success(account);
    }

    @Override
    @CacheEvict(value = "cache2",key = "#id")
    public Result deleteAccount(Integer id) {
        Result result = new Result();
        baseMapper.deleteById(id);
        putMsg(result, Status.SUCCESS);
        result.setData(Boolean.TRUE);
        return result;
    }

    @Override
    @CachePut(value = "cache2",key = "#account.id")// 在这里更新缓存，key是作为Redis缓存的key,condition做条件判断
    public Result updateAccount(Account account) {
        baseMapper.updateById(account);
        return Result.success(account);
    }

    @Override
    @Cacheable(value = "cache2", key = "#id")
    public Result<Account> queryAccountById(Integer id) {
        Account account = baseMapper.selectById(id);
        return Result.success(account);
    }
}
