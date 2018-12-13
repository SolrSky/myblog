package com.carlos.blog.controller.api;

import com.carlos.blog.annotation.MyLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Solrsky
 * @date 2018/12/11
 */
@Api(value = "api访问入口", tags = "1.0.0")
@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @MyLog
    @ApiOperation(value = "api入口", notes = "一切访问都先通过该接口")
    @RequestMapping("/manager")
    public void manager(HttpServletRequest request, HttpServletResponse response){

    }
}
