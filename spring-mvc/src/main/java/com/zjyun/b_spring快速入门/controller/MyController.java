package com.zjyun.b_spring快速入门.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@Controller
public class MyController {
    @RequestMapping("/show")
    public void show() {
        System.out.println("MyController#show()");
    }
}
