package com.zjyun.spring.非web项目启动.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/29
 */
@Configuration
@ComponentScan("com.zjyun.spring.非web项目启动.service")
@ComponentScan("com.zjyun.spring.非web项目启动.model")
public class AppConfig {

}
