package com.zjyun.a_spring整合web.config;

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

@ComponentScan("com.zjyun.a_spring整合web")
@MapperScan("com.zjyun.a_spring整合web.mapper")

@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MyBatisConfig.class})


public class AppConfig {
}
