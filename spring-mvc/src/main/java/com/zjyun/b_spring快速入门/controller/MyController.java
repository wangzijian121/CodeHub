package com.zjyun.b_spring快速入门.controller;

import com.zjyun.b_spring快速入门.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@Controller
public class MyController {

    @Autowired
    private MyService myService;

    @RequestMapping("/show")
    public void show() {
        System.out.println("MyController#show(),myService:" + myService);
    }
}
