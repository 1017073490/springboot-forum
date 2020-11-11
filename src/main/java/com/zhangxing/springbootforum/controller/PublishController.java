package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.mapper.QuestionMapper;
import com.zhangxing.springbootforum.mapper.UserMapper;
import com.zhangxing.springbootforum.model.Question;
import com.zhangxing.springbootforum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/10 9:21
 */
@Controller
public class PublishController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("titleInput") String titleInput,
            @RequestParam("descriptionInput") String descriptionInput,
            @RequestParam("tagInput") String tagInput,
            HttpServletRequest request, Model model
    ) {
        model.addAttribute("titleInput", titleInput);
        model.addAttribute("descriptionInput", descriptionInput);
        model.addAttribute("tagInput", tagInput);
        if (titleInput == null || titleInput.equals("")) {
            model.addAttribute("userError", "标题不能为空");
            return "publish";
        }
        if (descriptionInput == null || descriptionInput.equals("")) {
            model.addAttribute("userError", "问题补充不能为空");
            return "publish";
        }
        if (tagInput == null || tagInput.equals("")) {
            model.addAttribute("userError", "标签不能为空");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                //在获取到定义的名为"token"的cookie时，去数据库中查找这个user对象，
                // index中也可以在有user时渲染出效果
                if (cookie.getName().equals("token")) {
                    String cookieValue = cookie.getValue();
                    user= userMapper.findByToken(cookieValue);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user == null) {
            model.addAttribute("userError", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTITLE(titleInput);
        question.setDESCRIPTION(descriptionInput);
        question.setTAGS(tagInput);
        question.setCREATOR_ID(user.getID());
        question.setCREATE_DATE(System.currentTimeMillis());
        question.setMODIFIED_DATE(question.getCREATE_DATE());
        questionMapper.createQuestion(question);
        return "redirect:/";
    }

}
