package com.zhangxing.springbootforum.mapper;

import com.zhangxing.springbootforum.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO question (TITLE,DESCRIPTION,CREATE_DATE,MODIFIED_DATE,CREATOR_ID,TAGS)" +
            " VALUES (#{TITLE},#{DESCRIPTION},#{CREATE_DATE},#{MODIFIED_DATE},#{CREATOR_ID},#{TAGS})")
    void createQuestion(Question question);

    @Select("SELECT * FROM question LIMIT #{offset}, #{size}")
    List<Question> queryAllList(@Param(value = "offset") Integer offset,
                                @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer count();

}
