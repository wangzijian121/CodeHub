package com.zjyun.spring.自定义后处理器.自定义PostProcessor后处理器;

import com.zjyun.spring.自定义后处理器.bean.Bean1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: 自定义PostProcessor 后处理器，分析底层实现原理。
 * @Author: Wang Zijian
 * @Date: 2024/5/27
 */
public class MyPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private static final Logger log = LoggerFactory.getLogger(MyPostProcessor.class);
    @Nullable
    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    /***
     * 配置后处理器
     * @param pvs the property values that the factory is about to apply (never {@code null})
     * @param bean the bean instance created, but whose properties have not yet been set
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("执行postProcessProperties");
        if (beanFactory == null) {
            throw new Error("未设置BeanFactory!");
        }
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        try {
            //通过反射获取findAutowiringMetadata方法。
            Method objectMethod = AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata",
                    String.class,
                    Class.class,
                    PropertyValues.class);
            objectMethod.setAccessible(true);
            //通过反射借用底层方法😅
            InjectionMetadata metadata = (InjectionMetadata) objectMethod.invoke(processor, beanName, Bean1.class, null);

            //metadata.inject(bean, beanName, null);
            //inject 内部原理
            //获取字段依赖
            Field field = bean.getClass().getDeclaredField(beanName);
            DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(field, false);
            Object o = beanFactory.doResolveDependency(dependencyDescriptor, null, null, null);
            System.out.println("inject内部原理-依赖的对象：" + o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("【实例化】前！");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("【实例化】后！");
        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【初始化】前！");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【初始化】后！");
        return null;
    }
}
