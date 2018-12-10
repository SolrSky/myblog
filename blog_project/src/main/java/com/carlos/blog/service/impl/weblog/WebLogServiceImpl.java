package com.carlos.blog.service.impl.weblog;

import com.carlos.blog.entity.weblog.WebLog;
import com.carlos.blog.mapper.weblog.WebLogMapper;
import com.carlos.blog.service.weblog.WebLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Solrsky
 * @date 2018/12/5
 */
@Service
public class WebLogServiceImpl implements WebLogService {

    @Autowired
    private WebLogMapper mapper;

    @Override
    public void save(WebLog webLog) {
        mapper.insertSelective(webLog);
    }
}
