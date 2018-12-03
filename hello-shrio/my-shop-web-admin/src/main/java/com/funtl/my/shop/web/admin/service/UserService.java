package com.funtl.my.shop.web.admin.service;

import com.funtl.my.shop.domain.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User login(String username, String password);

    User findById(String username);
}
