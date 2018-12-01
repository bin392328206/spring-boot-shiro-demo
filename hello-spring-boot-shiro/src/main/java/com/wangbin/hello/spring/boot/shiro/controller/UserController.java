package com.wangbin.hello.spring.boot.shiro.controller;


import com.wangbin.hello.spring.boot.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {



    /**
     * 测试spring-boot
     *
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println("hello,spring-boot");
        return "ok";
    }


    @RequestMapping("testThymeleaf")
    public String testThymeleaf(Model model) {
        model.addAttribute("message", "hello-spring-boot-shiro");
        return "test";
    }


    @RequestMapping("/add")
    public String add() {

        return "/user/add";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }




    @RequestMapping("/update")
    public String update() {
        return "/user/update";
    }

    @RequestMapping(value = "/tologin")
    public String tologin(@ModelAttribute("message") String message,Model model) {
        model.addAttribute("message",message);
        return "/login";
    }


    @RequestMapping(value = "login" ,method = RequestMethod.POST)
    public String login(String name, String password, Model model, RedirectAttributes redirectAttributes) {
        /**
         * shiro进行认证操作
         */

        //获得Subject
        Subject subject = SecurityUtils.getSubject();

        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //执行登陆方法
        try {
            subject.login(token);
            return "redirect:/testThymeleaf";
            //登陆成功
        } catch (UnknownAccountException e) {
            //登陆失败并且表示用户名不存在
        redirectAttributes.addFlashAttribute("message", "用户名不存在");
        return "redirect:/tologin";


        } catch (IncorrectCredentialsException e) {
        //登陆失败并且表示密码错误
        redirectAttributes.addFlashAttribute("message", "用户密码错误");
        return "redirect:/tologin";


        }

    }







}
