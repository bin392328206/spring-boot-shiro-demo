package com.wangbin.hello.spring.boot.shiro.mapper;


import com.wangbin.hello.spring.boot.shiro.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findByName(String name);

    User findById(int id);

}
