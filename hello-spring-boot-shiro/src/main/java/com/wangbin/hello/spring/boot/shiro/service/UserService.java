package com.wangbin.hello.spring.boot.shiro.service;


import com.wangbin.hello.spring.boot.shiro.entity.User;

public interface UserService {

    User findeByName(String name);

    User findeById(int id);
}
