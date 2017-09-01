package com.zjl.test.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zjl.test.service.TestService;

import javax.annotation.Resource;

public class TestAction extends ActionSupport {

    @Resource
    private TestService testService;

    @Override
    public String execute() throws Exception {
        testService.say();
        return SUCCESS;
    }
}
