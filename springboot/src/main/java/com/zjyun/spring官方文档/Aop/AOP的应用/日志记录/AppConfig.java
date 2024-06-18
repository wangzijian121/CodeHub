package com.zjyun.spring官方文档.Aop.AOP的应用.日志记录;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.zjyun.spring官方文档.Aop.AOP的应用.日志记录")
public class AppConfig {
}
