package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.dto.AccessTokenDTO;
import com.zhangxing.springbootforum.provider.GithubProvider;
import com.zhangxing.springbootforum.dto.GithubUserDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 9:20
 */
@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.client.redirect_uri}")
    private String clientRedirect_uri;

    @GetMapping("/callbackAuth")
    public String githubCallback(@RequestParam(name = "code") String code,
                                 @RequestParam(name = "state") String state,
                                 HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();

        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(clientRedirect_uri);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO user = githubProvider.getGithubUser(accessToken);
        if (user!=null){
            //登录成功
            //写cookie和session
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }

}
