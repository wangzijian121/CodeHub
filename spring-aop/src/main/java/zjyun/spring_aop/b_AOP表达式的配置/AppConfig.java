package zjyun.spring_aop.b_AOP表达式的配置;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/8
 */
@Configuration
@ComponentScan("zjyun.spring_aop.b_AOP表达式的配置")//替代：<context:component-scan base-package="com.xxx.xxx">
@EnableAspectJAutoProxy(proxyTargetClass = true) //替代：<aop:aspectj-autoproxy>
public class AppConfig {

}
