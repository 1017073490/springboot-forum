package com.zhangxing.springbootforum.dto;

import lombok.Data;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 9:55
 */
@Data
public class AccessTokenDTO {
    public String client_id;
    public String client_secret;
    public String code;
    public String redirect_uri;
    public String state;
}
