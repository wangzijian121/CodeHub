package com.zjyun.springboot.读取配置文件;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Description:使用Environment 对象获取配置
 * @Author: Wang Zijian
 * @Date: 2024/7/15
 */
@RestController
public class ReadYmlByEnvironment {


    private Environment env;

    @Autowired
    public ReadYmlByEnvironment(Environment environment) {
        this.env = environment;
    }

    @GetMapping("/readYmlWithEnvironmentObject")
    public String readYml() {
        System.out.println(String.format("port= %s", env.getProperty("server.port")));
        return null;
    }
}
