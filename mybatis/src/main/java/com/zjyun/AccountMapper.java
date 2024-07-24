package com.zjyun;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.impl.PerpetualCache;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/7/22
 */
//@CacheNamespace(implementation = PerpetualCache.class)
public interface AccountMapper {
    Account selectAccount(int id);
}
