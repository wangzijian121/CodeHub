package com.zjyun.springboot.springboot集成mp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer balance;
}
