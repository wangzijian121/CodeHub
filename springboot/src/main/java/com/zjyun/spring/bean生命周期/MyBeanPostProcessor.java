package com.zjyun.spring.bean生命周期;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/3
 */
@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("person")) {
            System.out.println("实例化之前执行！ "+LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals("person")) {
            System.out.println("postProcessProperties "+LocalTime.now());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("person")) {
            System.out.println("实例化之后执行！ "+LocalTime.now());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (beanName.equals("person")) {

            System.out.println("初始化前执行！ "+LocalTime.now());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("person")) {

            System.out.println("初始化后执行！ "+LocalTime.now());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
