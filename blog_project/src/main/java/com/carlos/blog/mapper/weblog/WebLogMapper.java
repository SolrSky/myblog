package com.carlos.blog.mapper.weblog;

import com.carlos.blog.entity.weblog.WebLog;

public interface WebLogMapper {
    int insert(WebLog record);

    int insertSelective(WebLog record);
}