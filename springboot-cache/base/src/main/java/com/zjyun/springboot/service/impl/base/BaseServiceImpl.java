package com.zjyun.springboot.service.impl.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjyun.springboot.enums.Status;
import com.zjyun.springboot.service.base.IBaseService;
import com.zjyun.springboot.utils.Result;

import java.text.MessageFormat;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/8/1
 */

public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService {

    @Override
    public void putMsg(Result result, Status status, Object... statusParams) {
        result.setCode(status.getCode());
        if (statusParams != null && statusParams.length > 0) {
            result.setMsg(MessageFormat.format(status.getMsg(), statusParams));
        } else {
            result.setMsg(status.getMsg());
        }
    }
}
