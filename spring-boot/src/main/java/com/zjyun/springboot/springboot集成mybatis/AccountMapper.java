package com.zjyun.springboot.springboot集成mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/7/16
 */
@Mapper
public interface AccountMapper {
    @Select("select * from  spring.account where id =#{id}")
    Account selectAccountById(Integer id);
}
