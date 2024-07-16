package com.zjyun.springboot.springboot集成mybatisplus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
