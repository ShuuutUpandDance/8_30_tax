package com.zjl.test.service.impl;

import com.zjl.test.dao.TestDao;
import com.zjl.test.service.TestService;
import com.zjl.test.entity.Person;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public void say() {
        System.out.println("service OK");
    }

    @Override
    public void save(Person person) {
        testDao.save(person);
        int i = 1/0;
    }

    @Override
    public Person findPerson(Serializable id) {
        save(new Person("com/zjl/test"));
        return testDao.findPerson(id);
    }
}
