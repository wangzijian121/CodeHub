package com.example.j2ee.service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zijian Wang
 */
@WebFilter("/user/*")
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("拦截到了user的url🙋‍♂️");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("初始化Filter！");
    }

}


