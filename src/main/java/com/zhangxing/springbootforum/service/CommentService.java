package com.zhangxing.springbootforum.service;

import com.zhangxing.springbootforum.enums.CommentTypeEnum;
import com.zhangxing.springbootforum.exception.CustomizerErrorCode;
import com.zhangxing.springbootforum.exception.CustomizerException;
import com.zhangxing.springbootforum.mapper.CommentMapper;
import com.zhangxing.springbootforum.mapper.QuestionMapper;
import com.zhangxing.springbootforum.model.Comment;
import com.zhangxing.springbootforum.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/14 9:52
 */
@Service
public class CommentService {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentMapper commentMapper;

    public void insert(Comment comment) {
        if (comment.getPARENT_ID() == null || comment.getPARENT_ID() == 0) {
            throw new CustomizerException(CustomizerErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getTYPE() == null || !CommentTypeEnum.isExist(comment.getTYPE())) {
            throw new CustomizerException(CustomizerErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getTYPE().equals(CommentTypeEnum.COMMENT.getType())) {
            //回復評論
            Comment dbComment = commentMapper.selectByParentID(comment.getPARENT_ID());
            if (dbComment==null){
                throw new CustomizerException(CustomizerErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回復問題
            Question dbQuestion = questionMapper.selectByParentID(comment.getPARENT_ID());
            if (dbQuestion==null){
                throw new CustomizerException(CustomizerErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }
    }
}
