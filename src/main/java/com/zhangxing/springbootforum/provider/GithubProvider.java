package com.zhangxing.springbootforum.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhangxing.springbootforum.dto.AccessTokenDTO;
import com.zhangxing.springbootforum.dto.GithubUserDTO;
import okhttp3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 9:52
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            String[] split = string.split("&");
            String token = split[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public GithubUserDTO getGithubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //url为GitHub的API网址
                .url("https://api.github.com/user")
                //新版的使用
                .header("Authorization", "token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUserDTO githubUserDTOJSON = JSON.parseObject(string, GithubUserDTO.class);
            System.out.println(githubUserDTOJSON);
            return githubUserDTOJSON;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
