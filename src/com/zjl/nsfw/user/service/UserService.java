package com.zjl.nsfw.user.service;

import com.zjl.nsfw.user.entity.User;

import java.io.Serializable;
import java.util.List;

public interface UserService {
    //增
    void save(User user);
    //改
    void update(User user);
    //删
    void delete(Serializable id);
    //查(id)
    User findObjectById(Serializable id);
    //查(all)
    List<User> findObjects();
}
