package com.zjl.test.service;

import com.zjl.test.entity.Person;

import java.io.Serializable;

public interface TestService {
    void say();

    void save(Person person);

    Person findPerson(Serializable id);
}
