package com.carlos.blog.listener;


import com.carlos.blog.entity.webuser.WebUser;
import com.carlos.blog.utils.MemberUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author Solrsky
 * @date 2018/12/6
 */
@WebListener
public class HttpSessionAttributeListener implements javax.servlet.http.HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        if ("userInfo".equals(se.getName())) {
            MemberUtil.setUser((WebUser)se.getValue());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        if ("userInfo".equals(se.getName())){
            MemberUtil.setUser((WebUser)se.getValue());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        if ("userInfo".equals(se.getName())){
            MemberUtil.remove();
        }
    }
}
