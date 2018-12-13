package com.carlos.blog.exception;

/**
 * 未登录异常
 * @author Solrsky
 * @date 2018/12/13
 */
public class NotLoginException extends Exception{

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public NotLoginException(String message) {
        this.message = message;
    }
}
