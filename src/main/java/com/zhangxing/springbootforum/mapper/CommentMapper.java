package com.zhangxing.springbootforum.mapper;

import com.zhangxing.springbootforum.model.Comment;
import com.zhangxing.springbootforum.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO question (TITLE,DESCRIPTION,CREATE_DATE,MODIFIED_DATE,CREATOR_ID,TAGS)" +
            " VALUES (#{TITLE},#{DESCRIPTION},#{CREATE_DATE},#{MODIFIED_DATE},#{CREATOR_ID},#{TAGS})")
    void createQuestion(Question question);

    @Select("SELECT * FROM question LIMIT #{offset}, #{size}")
    List<Question> queryAllList(@Param(value = "offset") Integer offset,
                                @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer count();

    @Select("SELECT * FROM question WHERE CREATOR_ID=#{userID} LIMIT #{offset}, #{size}")
    List<Question> queryAllListPlusUser(@Param(value = "userID") Integer userID,
                                @Param(value = "offset") Integer offset,
                                @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM question WHERE CREATOR_ID=#{userID}")
    Integer countByUserID(@Param(value = "userID") Integer userID);

    @Select("SELECT * FROM question WHERE ID=#{id}")
    Question getByID(@Param(value = "id") Integer id);

    @Update("UPDATE question SET TITLE=#{TITLE}, DESCRIPTION=#{DESCRIPTION}," +
            " MODIFIED_DATE=#{MODIFIED_DATE}, TAGS=#{TAGS} WHERE ID=#{ID}")
    void updateQuestion(Question question);

    @Update("UPDATE question SET VIEW_COUNT=#{VIEW_COUNT} WHERE ID=#{ID}")
    void updateQuestionView(@Param(value = "VIEW_COUNT") int view_count,
                            @Param(value = "ID") int ID);

    @Insert("INSERT INTO comment (PARENT_ID,TYPE,COMMENTATOR_ID,CREATE_DATE,MODIFIED_DATE,CONTENT)" +
            " VALUES (#{PARENT_ID},#{TYPE},#{COMMENTATOR_ID},#{CREATE_DATE},#{MODIFIED_DATE},#{CONTENT})")
    void insert(Comment comment);

    Comment selectByParentID(Long parent_id);

}
