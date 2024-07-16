package com.zjyun.springboot.springboot集成mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/7/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String name;
    private Integer balance;
}
