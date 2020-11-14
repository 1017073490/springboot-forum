package com.zhangxing.springbootforum.service;

import com.zhangxing.springbootforum.dto.PageDTO;
import com.zhangxing.springbootforum.dto.QuestionDTO;
import com.zhangxing.springbootforum.exception.CustomizerErrorCode;
import com.zhangxing.springbootforum.exception.CustomizerException;
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
        Integer totalPage = 0;

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (totalPage <= 1) {
            totalPage = 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        pageDTO.setPagination(totalPage, page);

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

    public PageDTO queryAllList(Integer userID, Integer page, Integer size) {
        //page和size传入用来分页，并进行基本逻辑转换
        Integer totalCount = questionMapper.countByUserID(userID);
        PageDTO pageDTO = new PageDTO();
        Integer totalPage = 0;
        if (totalCount == 0) {
            totalPage = 1;
        } else if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        pageDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Question> questionList = questionMapper.queryAllListPlusUser(userID, offset, size);
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

    public QuestionDTO getByID(Integer id) {
        Question question = questionMapper.getByID(id);
        if (question == null) {
            throw new CustomizerException(CustomizerErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findByID(question.getCREATOR_ID());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getID() == null) {
            question.setCREATE_DATE(System.currentTimeMillis());
            question.setMODIFIED_DATE(question.getCREATE_DATE());
            questionMapper.createQuestion(question);
        } else {
            //不是第一次创建问题，更新
            question.setMODIFIED_DATE(System.currentTimeMillis());
            question.setTITLE(question.getTITLE());
            question.setDESCRIPTION(question.getDESCRIPTION());
            question.setTAGS(question.getTAGS());
            questionMapper.updateQuestion(question);
        }
    }

    public void addView(Integer id) {
        Question question = questionMapper.getByID(id);
        Integer view_count = question.getVIEW_COUNT() + 1;
        questionMapper.updateQuestionView(view_count, question.getID());
    }
}
