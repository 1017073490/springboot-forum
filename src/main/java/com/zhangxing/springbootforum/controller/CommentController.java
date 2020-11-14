package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.dto.CommentDTO;
import com.zhangxing.springbootforum.dto.ResponseDTO;
import com.zhangxing.springbootforum.exception.CustomizerErrorCode;
import com.zhangxing.springbootforum.mapper.CommentMapper;
import com.zhangxing.springbootforum.mapper.QuestionMapper;
import com.zhangxing.springbootforum.model.Comment;
import com.zhangxing.springbootforum.model.User;
import com.zhangxing.springbootforum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/13 21:13
 */
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(
            //!!!@RequestBody 用来接收json对应的对象里的字段有大写，所以接收不到，谨记
            @RequestBody CommentDTO commentDTO, HttpServletRequest request
//            @RequestParam(name = "PARENT_ID") Long PARENT_ID,
//            @RequestParam(name = "TYPE") Integer TYPE,
//            @RequestParam(name = "CONTENT") String CONTENT
    ) {
        System.out.println(commentDTO.toString());
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseDTO.errorOf(CustomizerErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setPARENT_ID(commentDTO.getParent());
        //根据Type进行不同处理
        comment.setTYPE(commentDTO.getType());
        comment.setCONTENT(commentDTO.getContent());
        comment.setCREATE_DATE(System.currentTimeMillis());
        comment.setMODIFIED_DATE(comment.getCREATE_DATE());
        comment.setCOMMENTATOR_ID(14);
        commentService.insert(comment);
        return ResponseDTO.okOf();
    }
}
