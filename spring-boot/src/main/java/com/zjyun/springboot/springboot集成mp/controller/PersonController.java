package com.zjyun.springboot.springboot集成mp.controller;

import com.zjyun.springboot.springboot集成mp.entity.Account;
import com.zjyun.springboot.springboot集成mp.entity.Person;
import com.zjyun.springboot.springboot集成mp.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 王子健
 * @since 2024-07-26
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping("/{id}")
    public Person getAccount(@PathVariable Integer id) {
        Person person = personService.getById(id);
        return person;
    }
}
