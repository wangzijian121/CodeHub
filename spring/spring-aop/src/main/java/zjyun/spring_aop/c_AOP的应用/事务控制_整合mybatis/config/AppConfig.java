package zjyun.spring_aop.c_AOP的应用.事务控制_整合mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement

@ComponentScan("zjyun.spring_aop.c_AOP的应用.事务控制_整合mybatis")
@MapperScan("zjyun.spring_aop.c_AOP的应用.事务控制_整合mybatis.mapper")

@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MyBatisConfig.class})
public class AppConfig {
}
