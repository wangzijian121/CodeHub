package com.zjyun.spring_ioc.模板方法模式;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/27
 */
public interface BeanPostProcessor {
    /**
     * 对依赖注入阶段的扩展
     */
    void inject();
}
