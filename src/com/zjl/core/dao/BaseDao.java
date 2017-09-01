package com.zjl.core.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    //增
    void save(T entity);
    //改
    void update(T entity);
    //删
    void delete(Serializable id);
    //查(id)
    T findObjectById(Serializable id);
    //查(all)
    List<T> findObjects();
}
