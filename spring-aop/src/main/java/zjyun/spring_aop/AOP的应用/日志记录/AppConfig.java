package zjyun.spring_aop.AOP的应用.日志记录;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.zjyun.spring官方文档.aop.AOP的应用.日志记录")
public class AppConfig {
}
