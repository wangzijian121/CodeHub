package com.zjyun.springboot.springboot集成mp.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjyun.springboot.springboot集成mp.entity.Account;
import com.zjyun.springboot.springboot集成mp.mapper.AccountMapper;
import com.zjyun.springboot.springboot集成mp.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangzijian
 * @since 2024-07-25
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    /**
     * 流式查询
     */
    public void streamingQuery() {
        Page<Account> page = new Page<>(1, 3);
        baseMapper.selectList(page, Wrappers.emptyWrapper(), new ResultHandler<Account>() {
            int count = 0;

            @Override
            public void handleResult(ResultContext<? extends Account> resultContext) {
                Account h2User = resultContext.getResultObject();
                System.out.println("当前处理第" + (++count) + "条记录: " + h2User);
                // 在这里进行你的业务处理，比如分发任务
            }
        });
    }
}
