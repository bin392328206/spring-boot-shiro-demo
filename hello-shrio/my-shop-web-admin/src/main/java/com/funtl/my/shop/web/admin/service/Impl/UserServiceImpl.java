package com.funtl.my.shop.web.admin.service.Impl;

import com.funtl.my.shop.domain.User;
import com.funtl.my.shop.web.admin.mapper.LoginMapper;

import com.funtl.my.shop.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User login(String username, String password) {
        User user =new User();
        user.setUsername(username);
        User loginID = loginMapper.findLoginID(user);
        if(loginID!=null){
            String s = DigestUtils.md5DigestAsHex(password.getBytes());
            if(s.equals(loginID.getPassword())){
                return loginID;
            }
        }

        return null;
    }



    public User findById(String username){
        return loginMapper.findById(username);

    }
}
