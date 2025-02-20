package com.zjyun.springboot.springboot集成mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjyun.springboot.springboot集成mp.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangzijian
 * @since 2024-07-25
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
