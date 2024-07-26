package com.zjyun.springboot.springboot集成mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjyun.springboot.springboot集成mp.entity.Person;
import com.zjyun.springboot.springboot集成mp.mapper.PersonMapper;
import com.zjyun.springboot.springboot集成mp.service.IPersonService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 王子健
 * @since 2024-07-26
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

    public void get() {
        System.out.println("PersonServiceImpl");
    }
}
