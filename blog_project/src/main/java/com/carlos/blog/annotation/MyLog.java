package com.carlos.blog.annotation;

import java.lang.annotation.*;

/**
 * 日志记录注解
 * @author Solrsky
 * @date 2018/12/5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default  "";
}
