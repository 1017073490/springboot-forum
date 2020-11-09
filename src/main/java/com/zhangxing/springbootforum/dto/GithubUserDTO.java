package com.zhangxing.springbootforum.dto;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 10:48
 */
public class GithubUserDTO {
    public String login;
    public String id;
    public String bio;

    @Override
    public String toString() {
        return "GithubUserDTO{" +
                "login='" + login + '\'' +
                ", id='" + id + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
