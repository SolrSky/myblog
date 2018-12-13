package com.carlos.blog.handler;

import com.carlos.blog.exception.ErrorSignException;
import com.carlos.blog.base.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获处理类
 * @author Solrsky
 * @date 2018/12/6
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(ErrorSignException.class)
    public BaseResponse doHandlerErrorSignException(ErrorSignException e){
        e.printStackTrace();
        BaseResponse response = new BaseResponse();
        response.setMsg(e.getMessage());
        response.setCode(500);
        response.setStatus(false);
        return response;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse doHandlerException(Exception e){
        e.printStackTrace();
        BaseResponse response = new BaseResponse();
        response.setMsg("系统异常");
        response.setStatus(false);
        response.setCode(500);
        return response;
    }


}
