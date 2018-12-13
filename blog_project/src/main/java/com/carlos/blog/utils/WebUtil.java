package com.carlos.blog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Solrsky
 * @date 2018/12/12
 */
@Component
public class WebUtil {

    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    private Environment environment;


    public boolean isDev(){
        return "dev".equalsIgnoreCase(env);
    }

    public boolean isTest(){
        return "test".equalsIgnoreCase(env);
    }

    public boolean isProduct(){
        return "pro".equalsIgnoreCase(env);
    }
}
