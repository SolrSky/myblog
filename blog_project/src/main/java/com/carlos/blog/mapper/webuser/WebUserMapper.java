package com.carlos.blog.mapper.webuser;

import com.carlos.blog.entity.webuser.WebUser;

public interface WebUserMapper {
    int insert(WebUser record);

    int insertSelective(WebUser record);
}