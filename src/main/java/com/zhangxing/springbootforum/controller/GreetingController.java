package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.mapper.UserMapper;
import com.zhangxing.springbootforum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/8 17:11
 */
@Controller
public class GreetingController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String greeting(HttpServletRequest request) {
        //此时使用request调用之前response设置的cookies（自己定义+系统默认）
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            //在获取到定义的名为"token"的cookie时，去数据库中查找这个user对象，
            // index中也可以在有user时渲染出效果
            if (cookie.getName().equals("token")) {
                String cookieValue = cookie.getValue();
                User user = userMapper.findByToken(cookieValue);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }
}
