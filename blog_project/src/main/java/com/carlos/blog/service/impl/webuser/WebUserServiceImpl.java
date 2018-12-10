package com.carlos.blog.service.impl.webuser;

import com.carlos.blog.entity.webuser.WebUser;
import com.carlos.blog.mapper.webuser.WebUserMapper;
import com.carlos.blog.service.webuser.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Solrsky
 * @date 2018/12/10
 */
@Service
public class WebUserServiceImpl implements WebUserService {

    @Autowired
    private WebUserMapper mapper;

    @Override
    public void save(WebUser user) {
        mapper.insert(user);
    }
}
