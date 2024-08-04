package com.zjyun.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjyun.springboot.entity.Account;
import com.zjyun.springboot.utils.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangzijian
 * @since 2024-07-30
 */
public interface IAccountService extends IService<Account> {

    /**
     * 分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    Result queryAccountListPaging(Integer pageNo, Integer pageSize);

    /**
     * 新增
     *
     * @param account
     * @return
     */
    Result saveAccount(Account account);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Result deleteAccount(Integer id);

    /**
     * 修改
     *
     * @param account
     * @return
     */
    Result updateAccount(Account account);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Result<Account> queryAccountById(Integer id);
}
