package com.carlos.blog.service;

import com.carlos.blog.entity.test.Test;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Solrsky
 * @date 2018/12/4
 */
public interface TestService {

    Test insert(Test test);

    PageInfo<Test> getAllByPage(int pageNum, int pageSize);

    List<Test> getAll();
}
