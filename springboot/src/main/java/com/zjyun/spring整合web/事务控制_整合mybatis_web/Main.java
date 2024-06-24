package com.zjyun.spring整合web.事务控制_整合mybatis_web;
import com.zjyun.spring整合web.事务控制_整合mybatis_web.model.Account;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/18
 */

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        for (String definitionName : context.getBeanDefinitionNames()) {
            System.out.println("🥔"+definitionName);
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
