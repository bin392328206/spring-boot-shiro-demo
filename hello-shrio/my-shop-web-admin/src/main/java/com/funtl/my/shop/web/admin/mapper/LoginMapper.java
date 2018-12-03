package com.funtl.my.shop.web.admin.mapper;

import com.funtl.my.shop.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {

    User findLoginID(User  user);


    User findById(String username);
}
