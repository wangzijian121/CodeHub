package com.zjyun.spring.beanfactory;

import com.zjyun.spring.config.AppConfig;
import com.zjyun.spring.model.Bean1;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;

/**
 * 通过基础的BeanFactory 注册BeanFactory 、Bean后处理器，对Bean中的注解进行解析，创建Bean对象。
 *
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/5/22
 */
public class BeanFactoryTest {
    public static void main(String[] args) {

        //定义一个BeanFactory的实现类 DefaultListableBeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //通过 Bean定义构造器，生成一个 Bean定义
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                .genericBeanDefinition(AppConfig.class).getBeanDefinition();
        //将 Bean定义 注册到bean工厂中
        beanFactory.registerBeanDefinition("AppConfig-object", beanDefinition);
        System.out.println("查看Bean定义：" + beanFactory.getBeanDefinition("AppConfig-object"));

        //BeanFactory本身并不具备解析能力，需要依赖注解处理器，此操作向BeanFactory中注册 BeanFactory 和 Bean 的后处理器。
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        //运行Bean工厂的后处理器：解析 @Configuration、@Bean、@ComponentScan等注解
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(x -> {
            System.out.println("当前bean工厂的后处理器🔄 " + x);
            x.postProcessBeanFactory(beanFactory);
        });

        //这里构造出的Bean单例由于没有bean注解的支持，解析不到bean2，还会导致后面的getBean(Bean1.class).getBean2()获取不到bean2
        //beanFactory.getBeansOfType(Bean1.class).values().forEach(x -> System.out.println("使用bean前处理器之前的Bean1实例🧐" + x));

        //运行 bean的后处理器：处理@Autowired、@Common注解。
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(x -> {
            System.out.println("当前bean的后处理器🔄 " + x);
            beanFactory.addBeanPostProcessor(x);
        });

        //打印所有bean定义
        for (String definitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println("🥔Bean定义：" + definitionName);
        }

        beanFactory.getBeansOfType(Bean1.class).values().forEach(x -> System.out.println("使用bean后处理器之前的Bean1实例🧐" + x));
        System.out.println(beanFactory.getBean(Bean1.class).getBean2());

    }
}
