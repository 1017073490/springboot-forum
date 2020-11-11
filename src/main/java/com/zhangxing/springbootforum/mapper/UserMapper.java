package com.zhangxing.springbootforum.mapper;

import com.zhangxing.springbootforum.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
