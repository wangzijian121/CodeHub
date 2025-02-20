package com.zjyun.springboot.controller;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
import com.zjyun.springboot.entity.Person;
import com.zjyun.springboot.service.IPersonService;
import com.zjyun.springboot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangzijian
 * @since 2024-08-04
 */
@RestController
@RequestMapping("/person")
public class PersonController {


    private final IPersonService personService;

    @Autowired
    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Result<Person> savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @DeleteMapping("/{id}")
    public Result deletePerson(@PathVariable Integer id) {
        return personService.deletePerson(id);
    }

    @PutMapping("{id}")
    public Result<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        person.setId(id);
        return personService.updatePerson(person);
    }

    @GetMapping("{id}")
    public Result<Person> queryPerson(@PathVariable Integer id) {
        return personService.queryPersonById(id);
    }
}
