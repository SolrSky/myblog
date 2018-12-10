package com.carlos.blog.controller.test;

import com.carlos.blog.annotation.MyLog;
import com.carlos.blog.base.BaseResponse;
import com.carlos.blog.entity.test.Test;
import com.carlos.blog.service.TestService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Solrsky
 * @date 2018/12/3
 */
@Api(value = "测试接口", description = "测试接口", tags = "1.0.0")
@RestController
public class TestController {

    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService service;

    @MyLog("获取信息")
    @ApiOperation(value = "获取信息", notes = "根据id获取内容信息")
    @ApiImplicitParam(value = "id", name = "信息id", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/index/{id}")
    public BaseResponse test(@PathVariable Integer id){
        PageInfo<Test> test = service.getAllByPage(1,20);
        return new BaseResponse(test);
    }

    @MyLog("设置信息")
    @ApiOperation(value = "设置信息", notes = "设置信息")
    @RequestMapping(value = "/set")
    public String set(){
        Test test = new Test();
        test.setContent("测试");
        service.insert(test);
        return test.getId().toString();
    }

    @MyLog
    @ApiOperation(value = "获取数据", notes = "获取分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "pageNum",name = "pageNum", required = true, dataType = "Integer"),
            @ApiImplicitParam(value = "pageSize", name = "pageSize", dataType = "Integer")
    })
    @RequestMapping(value = "/get")
    public BaseResponse getAll(Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize){
        PageInfo<Test> page = service.getAllByPage(pageNum, pageSize);
        return new BaseResponse(page);
    }
}
