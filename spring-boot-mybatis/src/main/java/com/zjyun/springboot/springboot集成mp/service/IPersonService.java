package com.zjyun.springboot.springboot集成mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjyun.springboot.springboot集成mp.entity.Person;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 王子健
 * @since 2024-07-26
 */
public interface IPersonService extends IService<Person> {
    void get();
}
