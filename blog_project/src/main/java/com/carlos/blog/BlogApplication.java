package com.carlos.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

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
}
