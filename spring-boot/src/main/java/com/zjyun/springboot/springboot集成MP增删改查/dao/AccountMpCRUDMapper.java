package com.zjyun.springboot.springboot集成MP增删改查.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjyun.springboot.springboot集成MP增删改查.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/7/16
 */
@Mapper
public interface AccountMpCRUDMapper extends BaseMapper<Account> {

}
