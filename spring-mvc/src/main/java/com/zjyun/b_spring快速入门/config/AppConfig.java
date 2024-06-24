package com.zjyun.b_spring快速入门.config;

import com.zjyun.a_spring整合web.config.JdbcConfig;
import com.zjyun.a_spring整合web.config.MyBatisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement

@ComponentScan("com.zjyun.b_spring快速入门")
public class AppConfig {
}
