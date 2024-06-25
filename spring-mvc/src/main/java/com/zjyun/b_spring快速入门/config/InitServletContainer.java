package com.zjyun.b_spring快速入门.config;

import com.zjyun.a_spring整合web.config.AppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 替换：
 * <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
 *          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *          xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
 *          version="4.0">
 *
 *     <!-- 1.Spring web提供的servlet监听器，加载到【RootApplicationContext】容器-->
 *     <listener>
 *         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 *     </listener>
 *
 *     <servlet>
 *         <servlet-name>DispatcherServlet</servlet-name>
 *         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 *         <!--2.扫描到Servlet容器【WebApplicationContext】-->
 *         <init-param>
 *             <param-name>contextClass</param-name>
 *             <param-value>com.zjyun.b_spring快速入门.config.SpringMvcAnnotationConfigWebApplicationContext</param-value>
 *         </init-param>
 *         <load-on-startup>2</load-on-startup>
 *     </servlet>
 *
 *     <servlet-mapping>
 *         <servlet-name>DispatcherServlet</servlet-name>
 *         <url-pattern>/mvc/*</url-pattern>
 *     </servlet-mapping>
 *
 * </web-app>
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/6/25
 */
public class InitServletContainer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Spring ROOT容器的核心配置类
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("执行！");
        Class[] classes = new Class[1];
        classes[0] = RootApplicationConfig.class;
        return classes;
    }

    /**
     * Spring Servlet容器的核心配置类
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcApplicationConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/mvc/*"};
    }
}
