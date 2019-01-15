package com.mtrhz.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * @author Solrsky
 * @date 2019/1/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

    /**
     * 操作类型
     * @return
     */
    public String operationType() default "";

    /**
     * 具体的操作名称
     * @return
     */
    public String operationName() default "";
}

