package com.zhangxing.springbootforum.dto;

import lombok.Data;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/13 21:30
 */
@Data
public class CommentDTO {
    private Long parent;
    private Integer type;
    private String content;
}
