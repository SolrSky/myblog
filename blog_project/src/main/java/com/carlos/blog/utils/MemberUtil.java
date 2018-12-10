package com.carlos.blog.utils;

import com.carlos.blog.entity.webuser.WebUser;

/**
 * 用户信息工具类
 * @author Solrsky
 * @date 2018/12/6
 */
public class MemberUtil {

    // 将用户信息放入ThreadLocal中
    private static ThreadLocal<WebUser> userInfo = new ThreadLocal<>();

    /**
     * 获取用户全部信息
     * @return
     */
    public static WebUser getUser(){
        return userInfo.get();
    }

    /**
     * 获取用户id
     * @return
     */
    public static Integer getUserId(){
        return userInfo.get().getUserid();
    }

    /**
     * 获取用户名
     * @return
     */
    public static String getUserName(){
        return userInfo.get().getUsername();
    }

    /**
     * 重新设置用户信息
     * @param webUser
     */
    public static void setUser(WebUser webUser){
        userInfo.set(webUser);
    }

    /**
     * 移除用户信息
     */
    public static void remove(){
        userInfo.remove();
    }
}
