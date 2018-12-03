package com.funtl.my.shop.web.admin.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.util.DigestUtils;

public class UserCredential extends SimpleCredentialsMatcher {


    //shiro加密的处理方式
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //需要将token密码 进行加密处理
        UsernamePasswordToken loginToken = (UsernamePasswordToken) token;
        //获得用户的密码
        char[] password = loginToken.getPassword();

        String s1 = String.valueOf(password);

        //获取加密后的密码
        String s = DigestUtils.md5DigestAsHex(s1.getBytes());

        //将密码获得到令牌当中
        loginToken.setPassword(s.toCharArray());
        //将用户的数据与数据库里面的数据做比较
        return super.doCredentialsMatch(token, info);


    }
}
