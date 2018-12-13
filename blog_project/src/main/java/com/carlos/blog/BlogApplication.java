package com.carlos.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Solrsky
 * @date 2018/12/3
 */
@SpringBootApplication
@MapperScan(basePackages = "com.carlos.blog.mapper")
@ServletComponentScan(basePackages = "com.carlos.blog.filter")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }


    /**
     * 设置匹配*.json后缀请求
     * @param dispatcherServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
        //servletServletRegistrationBean.addUrlMappings("*.json");
        //servletServletRegistrationBean.addUrlMappings("*.html");
        //servletServletRegistrationBean.addUrlMappings("/kaptcha");
        return servletServletRegistrationBean;
    }

}
