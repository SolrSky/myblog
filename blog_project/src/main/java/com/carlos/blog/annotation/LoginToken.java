package com.carlos.blog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Solrsky
 * @date 2018/12/11
 */
@Target({ElementType.METHOD,
         ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginToken {
}
