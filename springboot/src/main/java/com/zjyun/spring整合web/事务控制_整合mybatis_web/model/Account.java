package com.zjyun.spring整合web.事务控制_整合mybatis_web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 用户账户
 *
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
public class Account {
    private int id;
    private String name;
    private long balance;
}
