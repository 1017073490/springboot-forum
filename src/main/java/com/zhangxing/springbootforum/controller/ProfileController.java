package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.dto.PageDTO;
import com.zhangxing.springbootforum.mapper.UserMapper;
import com.zhangxing.springbootforum.model.User;
import com.zhangxing.springbootforum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/12 10:11
 */
@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model, HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "2") Integer size
    ) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        PageDTO pageDTO = questionService.queryAllList(user.getID(), page, size);
        model.addAttribute("pagination", pageDTO);
        return "profile";
    }
}
