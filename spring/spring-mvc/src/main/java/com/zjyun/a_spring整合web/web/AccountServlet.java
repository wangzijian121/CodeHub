package com.zjyun.a_spring整合web.web;

import com.zjyun.a_spring整合web.AccountService;
import com.zjyun.a_spring整合web.model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/24
 */
@WebServlet(urlPatterns = "/account_tran")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("执行get请求！");
        //自定义获取
        //AnnotationConfigApplicationContext context =
        //        (AnnotationConfigApplicationContext) req.getServletContext().getAttribute("applicationContext");
        //通过Spring-web获取
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext());
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
