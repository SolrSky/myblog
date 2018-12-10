package com.carlos.blog.filter;

import com.carlos.blog.entity.webuser.WebUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Solrsky
 * @date 2018/12/6
 */
@WebFilter(urlPatterns = "/*")
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        WebUser userSession = (WebUser) request.getSession().getAttribute("userInfo");
        if(null != userSession){
            // 先销毁原来的session值
            request.getSession().removeAttribute("userInfo");
            // 重新设置session值
            request.getSession().setAttribute("userInfo", userSession);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
