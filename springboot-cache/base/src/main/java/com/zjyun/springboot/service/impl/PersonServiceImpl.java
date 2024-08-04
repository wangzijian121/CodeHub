package com.zjyun.springboot.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.*;
import com.zjyun.springboot.entity.Person;
import com.zjyun.springboot.enums.Status;
import com.zjyun.springboot.mapper.PersonMapper;
import com.zjyun.springboot.service.IPersonService;
import com.zjyun.springboot.service.impl.base.BaseServiceImpl;
import com.zjyun.springboot.utils.Result;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * person server 层，使用阿里的jetCache作为缓存
 *
 * @author wangzijian
 * @since 2024-08-04
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl<PersonMapper, Person> implements IPersonService {

    @Override
    public Result savePerson(Person person) {
        System.out.println("创建缓存！");
        //personCache.put(Long.valueOf(person.getId()),person);
        baseMapper.insert(person);
        Result<Person> result = Result.success(person);
        return result ;
    }


    @Override
    @CacheInvalidate(name = "userCache.result.", key = "#id")
    public Result deletePerson(Integer id) {
        Result result = new Result();
        baseMapper.deleteById(id);
        putMsg(result, Status.SUCCESS);
        result.setData(Boolean.TRUE);
        return result;
    }

    @Override
    @CacheUpdate(name = "userCache.result.", key = "#person.id", value = "#result")
    public Result updatePerson(Person person) {
        baseMapper.updateById(person);
        Result<Person> result = Result.success(person);
        return result;
    }

    @Override
    @Cached(name="userCache.result.", key="#id", expire = 3600,cacheType= CacheType.REMOTE)
    public Result<Person> queryPersonById(Integer id) {
        Person person = baseMapper.selectById(id);
        return Result.success(person);
    }
}
