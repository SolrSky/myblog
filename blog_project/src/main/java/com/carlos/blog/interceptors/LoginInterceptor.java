package com.carlos.blog.interceptors;

import com.carlos.blog.annotation.LoginToken;
import com.carlos.blog.exception.LoginExpiredException;
import com.carlos.blog.exception.NotLoginException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 登录拦截器
 * @author Solrsky
 * @date 2018/12/12
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断该url对应的方法是否有logintoken标记
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        LoginToken loginToken = method.getAnnotation(LoginToken.class);
        // 判断请求的方法上是否有LoginToken注解
        boolean isNeedLogin1 = method.isAnnotationPresent(LoginToken.class);
        // 判断请求的类上是否有LoginToken注解
        boolean isNeedLogin2 = ((HandlerMethod) handler).getBeanType().isAnnotationPresent(LoginToken.class);
        // 只要一个需要登录，就必须判断请求中的token参数，是否有效
        if(isNeedLogin1 || isNeedLogin2){
            String token = request.getParameter("token");
            if(StringUtils.isBlank(token)){
                throw new NotLoginException("未登录");
            }

            Object userId = redisTemplate.opsForValue().get(token);
            if(null == userId){
                throw new LoginExpiredException("登录过期");
            }

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
