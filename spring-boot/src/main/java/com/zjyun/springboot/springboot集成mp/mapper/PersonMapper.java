package com.zjyun.springboot.springboot集成mp.mapper;

import com.zjyun.springboot.springboot集成mp.entity.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王子健
 * @since 2024-07-26
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {

}
