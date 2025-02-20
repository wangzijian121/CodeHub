package com.zjyun.starter.configuration;
 
import com.zjyun.starter.utils.WoniuUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
@EnableConfigurationProperties(WoniuProperties.class)
@ConditionalOnClass(WoniuUtil.class)
@ConditionalOnProperty(prefix = "zjyun", name = "enabled", havingValue = "true")
public class WoniuAutoConfiguration {
 
    private final WoniuProperties woniuProperties;
 
    public WoniuAutoConfiguration(WoniuProperties woniuProperties) {
        this.woniuProperties = woniuProperties;
    }
 
    @Bean
    public WoniuUtil woniuUtil() {
        return new WoniuUtil(woniuProperties.getPattern());
    }
}