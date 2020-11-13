package com.zhangxing.springbootforum.mapper;

import com.zhangxing.springbootforum.model.User;
import org.apache.ibatis.annotations.*;

/**
 * @author zhangxing
 * @Description:
 * @date 2020/11/9 19:13
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO github_user (LOGIN_ID,TOKEN,CREATE_DATE,MODIFIED_DATE,ACCOUNT_ID,BIO,AVATAR_URL)" +
            " VALUES (#{LOGIN_ID},#{TOKEN},#{CREATE_DATE},#{MODIFIED_DATE},#{ACCOUNT_ID},#{BIO},#{AVATAR_URL})")
    void insert(User user);

    @Select("SELECT * FROM github_user WHERE TOKEN=#{TOKEN}")
    User findByToken(@Param("TOKEN") String cookieValue);

    @Select("SELECT * FROM github_user WHERE ID=#{ID}")
    User findByID(@Param("ID") Integer creator_id);

    @Select("SELECT * FROM github_user WHERE ACCOUNT_ID=#{ACCOUNT_ID}")
    User findByAccountID(@Param("ACCOUNT_ID") String account_id);

    @Update("UPDATE github_user SET LOGIN_ID=#{LOGIN_ID}, TOKEN=#{TOKEN}," +
            " MODIFIED_DATE=#{MODIFIED_DATE}, BIO=#{BIO}, AVATAR_URL=#{AVATAR_URL}")
    void update(User user);
}
