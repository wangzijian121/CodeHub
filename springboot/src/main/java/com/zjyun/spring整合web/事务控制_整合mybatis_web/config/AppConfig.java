package com.zjyun.spring整合web.事务控制_整合mybatis_web.config;

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

@ComponentScan("com.zjyun.spring整合web.事务控制_整合mybatis_web")
@MapperScan("com.zjyun.spring整合web.事务控制_整合mybatis_web.mapper")

@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MyBatisConfig.class})


public class AppConfig {
}
