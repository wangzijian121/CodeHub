package com.example.j2ee.service;

import com.example.j2ee.bean.Person;
import com.example.j2ee.dao.VirtualDatabase;
import com.example.utils.StringUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


/**
 * 使用Servlet 实现的登录
 * http://localhost:8081/myAge?name=wangzijian&password=123456
 *
 * @author zijian Wang
 */
@WebServlet(name = "获取年龄", value = "/login")
public class MyHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //判断是否有SessionId
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("sessionId") && VirtualDatabase.map.containsKey(cookie.getValue())) {
                //提示
                HttpSession session = VirtualDatabase.map.get(cookie.getValue());
                response.setContentType("text/html;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.print("通过Session登录成功，你的信息是：" + session.getAttribute("person").toString());
                writer.close();
                response.sendRedirect("person.jsp");

                return;
            }
        }
        Person person = new Person(request.getParameter("name"),
                request.getParameter("password"),
                request.getParameter("username"),
                request.getParameter("age"));

        if (person.getUsername() == null || person.getPassword() == null) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print("请输入用户名和密码！");
            writer.close();
        }

        if (("wangzijian").equals(person.getUsername()) && ("123456".equals(person.getPassword()))) {
            System.out.println("登录成功！");
            String sessionId = StringUtils.getRandomStr(10);
            System.out.println("sessionId: " + sessionId);
            //生成SessionId
            HttpSession session = request.getSession();
            person.setName("王子健");
            person.setAge(String.valueOf(new Random().nextInt(30)));
            session.setAttribute("person", person);
            //Save Session
            VirtualDatabase.map.put(sessionId, session);
            //response add Cookie
            response.addCookie(new Cookie("sessionId", sessionId));
            //提示
            response.setContentType("text/html;charset=utf-8");
//            PrintWriter writer = response.getWriter();
//            writer.print("登录成功，你的信息是：" + session.getAttribute("person").toString());
//            writer.close();
            response.sendRedirect("person.jsp");
        } else {
            //提示
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print("登录失败!");
            writer.close();
        }
        VirtualDatabase.map.keySet().stream().forEach(
                x -> System.out.println("当前map存储的sessionId：" + x));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

}
