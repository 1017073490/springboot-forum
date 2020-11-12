package com.zhangxing.springbootforum.interceptor;

import com.zhangxing.springbootforum.mapper.UserMapper;
import com.zhangxing.springbootforum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/12 14:46
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //此时使用request调用之前response设置的cookies（自己定义+系统默认）
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
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
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
