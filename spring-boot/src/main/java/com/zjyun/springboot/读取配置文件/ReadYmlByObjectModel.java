package com.zjyun.springboot.读取配置文件;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:使用Environment 对象获取配置
 * @Author: Wang Zijian
 * @Date: 2024/7/15
 */
@RestController
public class ReadYmlByObjectModel {


    private Datasource datasource;

    @Autowired
    public ReadYmlByObjectModel(Datasource datasource) {
        this.datasource = datasource;
    }

    @GetMapping("/readYmlWithObjectModel")
    public String readYmlWithObjectModel() {
        System.out.println(String.format("datasource= %s", datasource));
        return null;
    }
}
