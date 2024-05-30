package com.zjyun.spring.自定义后处理器.自定义BeanFactoryPostProcessor后处理器;

import com.zjyun.spring.自定义后处理器.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

/**
 * @Description: 实现自定义工厂后处理器，加载@ComponmentScan、解析类中的@Component @Bean等注解.
 * @Author: Wang Zijian
 * @Date: 2024/5/28
 */
@Slf4j(topic = "c.MyBeanFactoryPostProcessor")
public class MyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    /**
     * 向后置处理器注册Bean定义
     *
     * @param registry the bean definition registry used by the application context
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {

        log.debug("向后置处理器注册Bean定义");
        CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
        registry.registerBeanDefinition(AppConfig.class.getName(), BeanDefinitionBuilder.genericBeanDefinition(AppConfig.class).getBeanDefinition());
        //通过注入进来的bean生成beanName
        ComponentScan scan = AnnotationUtils.findAnnotation(AppConfig.class, ComponentScan.class);
        try {
            MetadataReader metadataReader = readerFactory.getMetadataReader(new ClassPathResource(AppConfig.class.getName().replace(".", "/") + ".class"));
            //@Bean
            Set<MethodMetadata> methods = metadataReader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
            for (MethodMetadata method : methods) {
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
                beanDefinitionBuilder.setFactoryMethodOnBean(method.getMethodName(),AppConfig.class.getName());
                registry.registerBeanDefinition(method.getMethodName(),beanDefinitionBuilder.getBeanDefinition());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (scan == null) {
            throw new Error("没有定义@ComponentScan注解！");
        }
        String referencePath = scan.basePackages()[0];
        String replaceStr = "classpath:" + referencePath.replace(".", "/") + "/*.class";
        //读取指定classpath 上的资源并转换为Resource
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources;
        try {
            resources = resolver.getResources(replaceStr);
            for (Resource resource : resources) {
                //通过metadataReader获取指定资源中的注解
                MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                //@Component类注解
                if (annotationMetadata.hasAnnotation(Component.class.getName())
                        || annotationMetadata.hasMetaAnnotation(Component.class.getName())) {
                    BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(metadataReader.getClassMetadata().getClassName()).getBeanDefinition();
                    registry.registerBeanDefinition(beanDefinition.getBeanClassName(), beanDefinition);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 配置一些BeanFactory 供容器使用
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.debug("bean工厂后处理,对Bean定义进行修改! ");
    }
}
