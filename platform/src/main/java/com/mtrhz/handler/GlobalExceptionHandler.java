package com.mtrhz.handler;

import com.mtrhz.base.BaseResponse;
import com.mtrhz.constant.ResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Solrsky
 * @date 2019/1/14
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public BaseResponse doHandlerException(Exception e){
        LOGGER.error("系统异常:{}", e.getMessage());
        BaseResponse response = new BaseResponse();
        response.fail(ResponseEnum.FAIL.getCode(), "系统异常:" + e.getMessage());
        return response;
    }
}
