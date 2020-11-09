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

    @Insert("INSERT INTO GITHUB_USER (LOGIN_ID,TOKEN,CREATE_DATE,MODIFIED_DATE)" +
            " VALUES (#{LOGIN_ID},#{TOKEN},#{CREATE_DATE},#{MODIFIED_DATE})")
    void insert(User user);

    @Select("SELECT * FROM GITHUB_USER WHERE TOKEN=#{TOKEN}")
    User findByToken(@Param("TOKEN") String cookieValue);
}
