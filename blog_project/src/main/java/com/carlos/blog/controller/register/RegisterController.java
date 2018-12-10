package com.carlos.blog.controller.register;

import com.carlos.blog.annotation.MyLog;
import com.carlos.blog.base.BaseResponse;
import com.carlos.blog.constant.Constant;
import com.carlos.blog.constant.ResponseEnum;
import com.carlos.blog.entity.webuser.WebUser;
import com.carlos.blog.service.webuser.WebUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Solrsky
 * @date 2018/12/10
 */
@Api(value = "注册相关", tags = "1.0.0")
@RestController
public class RegisterController {

    private final static Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private WebUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @MyLog("注册")
    @ApiOperation(value = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "用户名"),
            @ApiImplicitParam(name = "手机号"),
            @ApiImplicitParam(name = "密码"),
            @ApiImplicitParam(name = "手机验证码")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseResponse register(@RequestParam String username, @RequestParam String mobile, @RequestParam String password, @RequestParam String validCode){

        BaseResponse response = new BaseResponse();

        String code = stringRedisTemplate.opsForValue().get(Constant.VALIDCODE_FOR_REGISTER_LEY_PRE + mobile);
        // 判断验证码是否过期
        if(StringUtils.isBlank(code)){
            response.setStatus(false);
            response.setMsg("验证码过期");
            response.setCode(ResponseEnum.FAIL.getValue());
            return response;
        }

        // 判断验证码是否正确
        if(!code.equals(validCode)){
            response.setStatus(false);
            response.setMsg("验证码错误");
            response.setCode(ResponseEnum.FAIL.getValue());
            return response;
        }


        WebUser user = new WebUser();
        user.setUsername(username);
        user.setMobile(mobile);
        user.setCreatetime(new Date());

        return response;
    }
}
