package com.zjyun.springboot;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Wang Zijian
 */
@SpringBootApplication
@MapperScan("com.zjyun.springboot.mapper")

// 【JetCache】开启JetCache使用注解方式创建cache
@EnableCreateCacheAnnotation
// 【JetCache】开启在方法上使用缓存注解
@EnableMethodCache(basePackages = "com.zjyun.springboot")
@EnableCaching
public class ApplicationByJetCache {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationByJetCache.class, args);
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println("🥔"+beanDefinitionName);
        }
    }
}