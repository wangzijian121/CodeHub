package com.zjyun.starter.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "zjyun")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class WoniuProperties {

    private String pattern = "yyyy-MM-dd hh:mm:ss"; // 日期格式，默认为"yyyy-MM-dd hh:mm:ss"

}