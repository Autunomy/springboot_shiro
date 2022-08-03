package com.hty.springboot_shiro.controller;

import com.hty.springboot_shiro.entity.User;
import com.hty.springboot_shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/index.jsp";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名出错");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码出错");
        }
        return "redirect:/login.jsp";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";

    }

    @RequestMapping("/register")
    public String register(User user){
        try {
            userService.registry(user);
            return "redirect:/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }

}
