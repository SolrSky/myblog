package com.carlos.blog.exception;

/**
 * @author Solrsky
 * @date 2018/12/12
 */
public class ErrorSignException extends Exception{

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ErrorSignException(String message) {
        this.message = message;
    }
}
