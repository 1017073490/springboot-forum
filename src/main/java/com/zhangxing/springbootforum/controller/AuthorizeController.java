package com.zhangxing.springbootforum.controller;

import com.zhangxing.springbootforum.dto.AccessTokenDTO;
import com.zhangxing.springbootforum.dto.GithubUserDTO;
import com.zhangxing.springbootforum.model.User;
import com.zhangxing.springbootforum.provider.GithubProvider;
import com.zhangxing.springbootforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 9:20
 */
@Controller
public class AuthorizeController {

    @Autowired
    UserService userService;

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
                                 HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        //将回调的参数定义进去
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(clientRedirect_uri);
        //getGithubUser调用获取token方法来获得token（这个token是经过处理的真正的token值）
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //getGithubUser调用需要传入token的链接（GitHub提供）来返回用户信息
        GithubUserDTO githubUserDTO = githubProvider.getGithubUser(accessToken);
        if (githubUserDTO != null) {
            //登录成功，user的信息都获取到了，进行设置。
            //在创建新用户之前应该查询数据库有无该用户
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setTOKEN(token);
            user.setLOGIN_ID(githubUserDTO.getLogin());
            user.setACCOUNT_ID(githubUserDTO.getId());
            user.setBIO(githubUserDTO.getBio());
            user.setAVATAR_URL(githubUserDTO.getAvatar_url());
            userService.createOrUpdate(user);
            //将所有的信息存入数据库，为持久化提供数据支撑
            //将token信息写入cookie，然后重定向到首页，首页有相应的逻辑判断
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        //首先清楚session、cookie
        request.getSession().removeAttribute("user");
        //设置同名并进行设置即可删除cookie
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
