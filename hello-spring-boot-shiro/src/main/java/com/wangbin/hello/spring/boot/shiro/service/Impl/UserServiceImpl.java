package com.wangbin.hello.spring.boot.shiro.service.Impl;

import com.wangbin.hello.spring.boot.shiro.entity.User;
import com.wangbin.hello.spring.boot.shiro.mapper.UserMapper;
import com.wangbin.hello.spring.boot.shiro.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findeByName(String name) {
        User user = userMapper.findByName(name);
        System.out.println(user.toString());

        return user;
    }


    @Override
    public User findeById(int id) {
        return userMapper.findById(id);
    }
}
