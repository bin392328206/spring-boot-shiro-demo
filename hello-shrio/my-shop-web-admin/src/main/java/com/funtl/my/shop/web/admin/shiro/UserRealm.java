package com.funtl.my.shop.web.admin.shiro;

import com.funtl.my.shop.domain.User;
import com.funtl.my.shop.web.admin.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

        return null;
    }

    //登陆认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //登陆认证   shiro安全中心调用realm查询用户真实信息，传递token数据
        //需要为Shiro安全中心提供真实的用户数据   需要根据用户名查询user对象

        //1.转换转化为usernamePasswordToken

        UsernamePasswordToken loginToken = (UsernamePasswordToken) token;

        //2.获取用户名
        String username = loginToken.getUsername();

        //3.根据用户名查询数据
        User user = userService.findById(username);

        //4.将查询到的用户真实信息返回给Shiro安全中心
        /**
         * 1.principal   表示用户的真实对象
         * 2.credentials 校验密码时使用的  真实的密码
         * 3.realmName   realm的名称
         */

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());


        return info;
    }
}
