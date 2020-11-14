package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.dto.QuestionDTO;
import com.zhangxing.springbootforum.mapper.QuestionMapper;
import com.zhangxing.springbootforum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/12 15:52
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(
            @PathVariable(name = "id") Integer id,
            Model model
    ){
        questionService.addView(id);
        QuestionDTO questionDTO = questionService.getByID(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
