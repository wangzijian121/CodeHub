package zjyun.spring_aop.AOP的应用.事务控制_整合mybatis;

import zjyun.spring_aop.AOP的应用.事务控制_整合mybatis.config.AppConfig;
import zjyun.spring_aop.AOP的应用.事务控制_整合mybatis.model.Account;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        for (String definitionName : context.getBeanDefinitionNames()) {
            System.out.println(definitionName);
        }
        AccountService accountService = context.getBean(AccountService.class);
        Account accountA = accountService.getAccount(1);
        Account accountB = accountService.getAccount(2);

        System.out.println("【账户】" + accountA);
        System.out.println("【账户】" + accountB);
        accountService.transfer(accountA, accountB, 1000);
        System.out.println("【账户】" + accountA);
        System.out.println("【账户】" + accountB);
    }
}
