package com.carlos.blog.mapper.test;

import com.carlos.blog.entity.test.Test;

import java.util.List;

/**
 * @author Solrsky
 * @date 2018/12/4
 */
public interface TestDao {

    Test insert(Test test);

    List<Test> getAll();
}
