package com.zhangxing.springbootforum.dto;

import lombok.Data;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 10:48
 */
@Data
public class GithubUserDTO {
    public String login;
    public String id;
    public String bio;
    private String avatar_url;
}
