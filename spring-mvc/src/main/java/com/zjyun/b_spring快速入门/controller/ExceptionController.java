package com.zjyun.b_spring快速入门.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

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
