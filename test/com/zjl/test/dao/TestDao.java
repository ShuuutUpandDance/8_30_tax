package com.zjl.test.dao;

import com.zjl.test.entity.Person;

import java.io.Serializable;

public interface TestDao {
    void save(Person person);

    Person findPerson(Serializable id);
}
