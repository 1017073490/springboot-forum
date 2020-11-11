package com.zhangxing.springbootforum.dto;

import com.zhangxing.springbootforum.model.User;
import lombok.Data;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/11 9:55
 */
@Data
public class QuestionDTO {
    private Integer ID;
    private String TITLE;
    private String DESCRIPTION;
    private Long CREATE_DATE;
    private Long MODIFIED_DATE;
    private Integer CREATOR_ID;
    private Integer COMMENT_COUNT;
    private Integer VIEW_COUNT;
    private Integer LIKE_COUNT;
    private String TAGS;
    private User user;
}
