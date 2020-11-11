package com.zhangxing.springbootforum.service;

import com.zhangxing.springbootforum.dto.PageDTO;
import com.zhangxing.springbootforum.dto.QuestionDTO;
import com.zhangxing.springbootforum.mapper.QuestionMapper;
import com.zhangxing.springbootforum.mapper.UserMapper;
import com.zhangxing.springbootforum.model.Question;
import com.zhangxing.springbootforum.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/11 9:57
 */
@Service
public class QuestionService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    public PageDTO queryAllList(Integer page, Integer size) {
        //page和size传入用来分页，并进行基本逻辑转换

        Integer totalCount = questionMapper.count();
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > pageDTO.getTotalPage()) {
            page = pageDTO.getTotalPage();
        }

        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.queryAllList(offset, size);
        //questionDTOList用于存放questionList+User相关的东西
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findByID(question.getCREATOR_ID());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question的全部属性先复制到questionDTO中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pageDTO.setQuestions(questionDTOList);


        return pageDTO;
    }
}
