package com.carlos.blog.exception;

/**
 * 登录过期异常
 * @author Solrsky
 * @date 2018/12/13
 */
public class LoginExpiredException extends Exception{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public LoginExpiredException(String message) {
        this.message = message;
    }
}
