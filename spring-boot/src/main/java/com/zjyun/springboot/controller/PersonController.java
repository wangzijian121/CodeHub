package com.zjyun.springboot.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @Description:  使用RESTFUL风格的【增删改查】
 * @Author: Wang Zijian
 * @Date: 2024/7/15
 */
@RestController
@RequestMapping("/person")
public class PersonController {


    @PostMapping
    public String add(@RequestBody Person person) {
        return String.format("add %s!", person);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id) {
        return String.format("deleteById %s!", id);
    }

    @PutMapping
    public String update(@RequestBody Person person) {
        return String.format("update %s!", person);
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Integer id) {
        return String.format("get %s!", id);
    }
}
