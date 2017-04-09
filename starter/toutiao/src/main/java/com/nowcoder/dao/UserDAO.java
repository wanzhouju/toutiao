package com.nowcoder.dao;

import com.nowcoder.model.News;
import com.nowcoder.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by wuzhaojun on 2017/4/7.
 */
@Mapper
public interface UserDAO {

    String TABLE_NAME = "user";

    String INSERT_FIELDS = " name, password, salt, head_url ";

    String SELECT_FIELDS = " id, name, password, salt, head_url ";

    @Insert({
            "insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") Values (#{name}, #{password}, #{salt}, #{headUrl})"
    })
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectById(int id);

    @Update({"update ", TABLE_NAME, " set password = #{password} where id=#{id}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where id = #{id}"})
    void deleteById(int id);
}
