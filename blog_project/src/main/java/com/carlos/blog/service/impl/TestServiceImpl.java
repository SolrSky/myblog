package com.carlos.blog.service.impl;

import com.carlos.blog.mapper.test.TestDao;
import com.carlos.blog.entity.test.Test;
import com.carlos.blog.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Solrsky
 * @date 2018/12/4
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao dao;

    @Override
    public Test insert(Test test) {
        return dao.insert(test);
    }

    @Override
    public PageInfo<Test> getAllByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Test> list = dao.getAll();
        PageInfo page = new PageInfo(list);
        return page;
    }

    @Override
    public List<Test> getAll() {
        return dao.getAll();
    }
}
