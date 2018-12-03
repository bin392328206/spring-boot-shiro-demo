package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.domain.User;
import com.funtl.my.shop.web.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;


    //用来跳转到登陆页面的
    @RequestMapping(value = "toLogin", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }


    //以前不是shiro的登陆方式
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login(@RequestParam(required = true) String username, @RequestParam(required = true) String password, HttpServletRequest httpServletRequest) {
//        User user = userService.login(username, password);
//        if (user != null) {
//            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
//            return "redirect:/main";
//            //登录成功
//
//        } else {
//            return "/WEB-INF/login.jsp";
//        }
//
//
//    }


    //shiro登陆验证


    @RequestMapping("login")
    public String login(String username, String password, Model model, HttpSession session) {
        /**
         * 1.判断用户输入的内容是否为空
         * 2.通过用户名和密码进行查询操作 得到user对象
         * 3.如果对象为null,证明用户名和密码不正确  编辑提示信息 页面跳转到登陆页面
         * 4.如果user对象不为null 则证明用户名和密码正确， 跳转到系统首页
         */

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            //账户和密码有一个为空
            return "login";
        }

        //获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //定义登陆的令牌，将账户和密码封装成一个令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //通过subject进行登陆操作


        try {
            subject.login(token);
            //获取用户的信息
            User user =(User) subject.getPrincipal();

            //获取Session
            subject.getSession().setAttribute("user",user);

            session.setAttribute("user",user);
            return "redirect:/main";




        } catch (AuthenticationException a) {
            //表示登陆失败
            model.addAttribute("message","用户名或密码错误");
            return "login";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message","发现未知的错误，请联系管理员");
            return "login";
        }



    }
}
