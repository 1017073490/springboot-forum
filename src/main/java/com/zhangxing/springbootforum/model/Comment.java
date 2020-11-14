package com.zhangxing.springbootforum.model;

import lombok.Data;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/13 21:06
 */
@Data
public class Comment {
    private Long ID;
    private Long PARENT_ID;
    private Integer TYPE;
    private Integer COMMENTATOR_ID;
    private Long CREATE_DATE;
    private Long MODIFIED_DATE;
    private Integer LIKE_COUNT;
    private Integer COMMENT_COUNT;
    private String CONTENT;
}
