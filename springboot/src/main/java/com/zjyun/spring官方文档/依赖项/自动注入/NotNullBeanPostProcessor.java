package com.zjyun.spring官方文档.依赖项.自动注入;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.lang.reflect.Field;

public class NotNullBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(bean);
                    if (value == null) {
                        throw new IllegalArgumentException("字段 " + field.getName() + " 在bean： " + beanName + "中必须是不为空的！");
                    }
                } catch (IllegalAccessException e) {
                    throw new BeansException("传入的依赖是null了！" + field.getName(), e) {
                    };
                }
            }
        }
        return true;
    }
}
