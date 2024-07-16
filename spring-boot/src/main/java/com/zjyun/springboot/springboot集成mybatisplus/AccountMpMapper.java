package com.zjyun.springboot.springboot集成mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/7/16
 */
@Mapper
public interface AccountMpMapper extends BaseMapper<Account> {

}
