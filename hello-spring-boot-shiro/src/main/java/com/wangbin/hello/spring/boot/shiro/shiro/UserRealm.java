package com.wangbin.hello.spring.boot.shiro.shiro;

import com.wangbin.hello.spring.boot.shiro.entity.User;
import com.wangbin.hello.spring.boot.shiro.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("走授权");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        User dbuser = userService.findeById(user.getId());
        info.addStringPermission(dbuser.getPerms());

        //添加授权字符串
//        info.addStringPermission("user:add");



        return info;
    }


    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("走认证");



        //从前面传过来的toke
        UsernamePasswordToken token =(UsernamePasswordToken) authenticationToken;
        //获得用户名
        User user = userService.findeByName(token.getUsername());

        System.out.println("这一步");
        //判断用户名
     if(user==null){
         //用户名不存在
         return null;

        }

        //判断密码
        return  new SimpleAuthenticationInfo(user,user.getPassword(),"");









    }


}
