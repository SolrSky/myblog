package com.carlos.blog.controller.login;

import com.carlos.blog.annotation.MyLog;
import com.carlos.blog.base.BaseResponse;
import com.carlos.blog.controller.test.TestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Solrsky
 * @date 2018/12/10
 */
@Api(value = "登录相关", tags = "1.0.0")
@RestController
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @MyLog("登录")
    @ApiOperation(value = "登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse login(@RequestParam String username, @RequestParam String password){

        return null;
    }


}
