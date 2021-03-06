package com.zhangxing.springbootforum.model;

import lombok.Data;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/10 10:32
 */
@Data
public class Question {
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
}
