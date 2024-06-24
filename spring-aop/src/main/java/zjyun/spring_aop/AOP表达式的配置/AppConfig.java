package zjyun.spring_aop.AOP表达式的配置;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/8
 */
@Configuration
@ComponentScan("com.zjyun.spring官方文档.aop.AOP表达式的配置")//替代：<context:component-scan base-package="com.xxx.xxx">
@EnableAspectJAutoProxy(proxyTargetClass = true) //替代：<aop:aspectj-autoproxy>
public class AppConfig {

}
