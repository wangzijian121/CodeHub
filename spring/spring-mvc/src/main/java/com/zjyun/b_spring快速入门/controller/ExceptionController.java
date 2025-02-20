package com.zjyun.b_spring快速入门.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@RestController
@RequestMapping("/ex")
public class ExceptionController {


    @RequestMapping(value = "ex1", method = RequestMethod.GET)
    public String ex1() {
        int i = 0 / 0;
        return "ex1";
    }

    @RequestMapping(value = "ex2", method = RequestMethod.GET)
    public String ex2() throws IOException {
        int i = 0 / 0;
        return "ex1";
    }
}
