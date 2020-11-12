package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.dto.PageDTO;
import com.zhangxing.springbootforum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/8 17:11
 */
@Controller
public class GreetingController {


    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String greeting(HttpServletRequest request,
                           Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "2") Integer size
                           ) {
        //查询数据，放入页面
        PageDTO pagination = questionService.queryAllList(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
