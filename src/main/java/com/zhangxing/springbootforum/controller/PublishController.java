package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.dto.QuestionDTO;
import com.zhangxing.springbootforum.model.Question;
import com.zhangxing.springbootforum.model.User;
import com.zhangxing.springbootforum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/10 9:21
 */
@Controller
public class PublishController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "titleInput",required = false) String titleInput,
            @RequestParam(value = "descriptionInput",required = false) String descriptionInput,
            @RequestParam(value = "tagInput",required = false) String tagInput,
            @RequestParam(value = "questionID",required = false) Integer questionID,
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
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("userError", "用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setID(questionID);
        question.setTITLE(titleInput);
        question.setDESCRIPTION(descriptionInput);
        question.setTAGS(tagInput);
        question.setCREATOR_ID(user.getID());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String rePublish(@PathVariable(name = "id") Integer questionID,
                            Model model) {
        //只是简单的回显
        QuestionDTO question = questionService.getByID(questionID);
        model.addAttribute("titleInput", question.getTITLE());
        model.addAttribute("descriptionInput", question.getDESCRIPTION());
        model.addAttribute("tagInput", question.getTAGS());
        model.addAttribute("questionID", question.getID());
        return "publish";
    }

}
