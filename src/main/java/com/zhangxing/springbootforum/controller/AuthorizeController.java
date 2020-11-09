package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.dto.AccessTokenDTO;
import com.zhangxing.springbootforum.mapper.UserMapper;
import com.zhangxing.springbootforum.model.User;
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
import java.util.UUID;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 9:20
 */
@Controller
public class AuthorizeController {

    @Autowired
    UserMapper userMapper;

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
        GithubUserDTO githubUserDTO = githubProvider.getGithubUser(accessToken);
        if (githubUserDTO!=null){
            //登录成功
            User user = new User();
            user.setTOKEN(UUID.randomUUID().toString());
            user.setLOGIN_ID(githubUserDTO.getLogin());
            user.setCREATE_DATE(System.currentTimeMillis());
            user.setMODIFIED_DATE(user.getCREATE_DATE());
            userMapper.insert(user);
            //写cookie和session
            request.getSession().setAttribute("user",githubUserDTO);
            return "redirect:/";
        }else {
            //登录失败
            return "redirect:/";
        }
    }

}
