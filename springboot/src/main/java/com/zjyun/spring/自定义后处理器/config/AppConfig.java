package com.zjyun.spring.自定义后处理器.config;

import com.zjyun.spring.自定义后处理器.bean.Bean1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/28
 */
@Configuration
@ComponentScan("com.zjyun.spring.自定义后处理器.bean")
public class AppConfig {
    @Bean
    public Bean1 getBean1(){
        return  new Bean1();
    }
}
