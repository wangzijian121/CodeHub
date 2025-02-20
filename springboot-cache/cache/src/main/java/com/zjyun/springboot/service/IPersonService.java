package com.zjyun.springboot.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.zjyun.springboot.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjyun.springboot.utils.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangzijian
 * @since 2024-08-04
 */
public interface IPersonService extends IService<Person> {

    /**
     * 新增
     *
     * @param person
     * @return
     */

    Result savePerson(Person person);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Result deletePerson(Integer id);

    /**
     * 修改
     *
     * @param person
     * @return
     */
    Result updatePerson(Person person);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Result<Person> queryPersonById(Integer id);
}
