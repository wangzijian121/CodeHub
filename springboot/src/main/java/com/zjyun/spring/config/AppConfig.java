package com.zjyun.spring.config;

import com.zjyun.spring.model.Bean1;
import com.zjyun.spring.model.Bean2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/22
 */
@Configuration
//@ComponentScan("com.zjyun.spring.model")
public class AppConfig {

    @Bean
    public Bean1 bean1(){
        return new Bean1();
    }
    @Bean
    public Bean2 bean2(){
        return new Bean2();
    }
}
