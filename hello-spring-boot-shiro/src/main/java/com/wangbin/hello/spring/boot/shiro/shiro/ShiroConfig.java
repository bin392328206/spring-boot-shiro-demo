package com.wangbin.hello.spring.boot.shiro.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */

@Configuration
public class ShiroConfig {


    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultSecurityManager SecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(SecurityManager);
        //shiro内置过滤器 用来拦截资源
        //常用过滤器
        /**
         * anon 无需认证（登陆） 可以访问
         * authc 必须认证才可以访问
         * user: 如果使用remeberme的功能可以直接访问
         * role: 改资源必须得到角色的权限才能访问
         * perms:该资源必须得到资源权限才可以访问
         */
        Map<String ,String> map =new LinkedHashMap<String, String>();


//        map.put("/add","authc");
//        map.put("/update","authc");
        ///拦截user下的所有请求
        map.put("/testThymeleaf","anon");
        map.put("/login","anon");

        //资源授权过滤器
        map.put("/add","perms[user:add]");
        map.put("/update","perms[user:update]");


        map.put("/*","authc");




        //设置未登录跳转页面
        shiroFilterFactoryBean.setLoginUrl("/tologin");








        //设置未授权跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        return shiroFilterFactoryBean;
    }


    /**.
     * 创建DefaultWebSecurityManger
     */
    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
        //需要关联Realm
        SecurityManager.setRealm(userRealm);
        return SecurityManager;
    }


    /**
     * 创建Realm
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }


    /***
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
