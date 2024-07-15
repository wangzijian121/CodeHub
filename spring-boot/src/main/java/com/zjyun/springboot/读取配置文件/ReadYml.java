package com.zjyun.springboot.读取配置文件;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 使用@Value注释获取配置值
 * @Author: Wang Zijian
 * @Date: 2024/7/15
 */
@RestController
public class ReadYml {

    @Value("${server.port}")
    private String port;

    @Value("${likes[0]}")
    private String like1;
    @Value("${users[0].name}")
    private String uname1;

    @Value("${output}")
    private String output;

    @Value("${t1}")
    private String t1;
    @Value("${t2}")
    private String t2;

    @GetMapping("/readYml")
    public String readYml() {
        System.out.println(String.format("port= %s", port));
        System.out.println(String.format("like1= %s", like1));
        System.out.println(String.format("users.name1= %s", uname1));
        System.out.println(String.format("output= %s", output));
        System.out.println(String.format("t1= %s", t1));
        System.out.println(String.format("t2= %s", t2));
        return null;
    }
}
