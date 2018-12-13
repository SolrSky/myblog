package com.carlos.blog.interceptors;

import com.carlos.blog.exception.ErrorSignException;
import com.carlos.blog.utils.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Solrsky
 * @date 2018/12/11
 */
@Component
public class ApiInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);

    @Autowired
    private WebUtil webUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入了拦截器 -->> [ preHandler() ]");
        logger.info("请求的原路径 -->> [ {} ]", request.getRequestURL());
        logger.info("进行参数验签 -->> ");
        if(!webUtil.isDev()){
            // 非开发环境获取请求中的sign
            String sign = request.getParameter("sign");
            if(!webUtil.isDev() && StringUtils.isBlank(sign)){
                throw new ErrorSignException("缺少签名");
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
