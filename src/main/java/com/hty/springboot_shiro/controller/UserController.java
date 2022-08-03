package com.hty.springboot_shiro.controller;

import com.hty.springboot_shiro.entity.User;
import com.hty.springboot_shiro.service.UserService;
import com.hty.springboot_shiro.util.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("code") String code,
                        HttpSession session){
        //比较验证码
        String code1 = (String) session.getAttribute("code");
        if(code1.equalsIgnoreCase(code)){
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
        }else{
            //验证码出错
            throw new RuntimeException("验证码错误");
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

    //验证码方法
    @RequestMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //验证码放入session中
        session.setAttribute("code",code);
        //将验证码存入图片
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/png");
        try {
            VerifyCodeUtils.outputImage(80,30,outputStream,code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
