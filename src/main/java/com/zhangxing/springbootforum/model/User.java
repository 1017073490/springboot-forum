package com.zhangxing.springbootforum.model;

import lombok.Data;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 19:17
 */

@Data
public class User {
    private Integer ID;
    private String LOGIN_ID;
    private String TOKEN;
    private Long CREATE_DATE;
    private Long MODIFIED_DATE;
    private String ACCOUNT_ID;
    private String BIO;
    private String AVATAR_URL;
}
