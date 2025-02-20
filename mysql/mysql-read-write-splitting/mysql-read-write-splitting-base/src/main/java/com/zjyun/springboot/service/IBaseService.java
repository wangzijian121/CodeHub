package com.zjyun.springboot.service;

import com.zjyun.springboot.enums.Status;
import com.zjyun.springboot.utils.Result;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/8/1
 */
public interface IBaseService {
    void putMsg(Result result, Status status, Object... statusParams);
}
