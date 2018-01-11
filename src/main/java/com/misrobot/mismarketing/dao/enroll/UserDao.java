package com.misrobot.mismarketing.dao.enroll;

import com.misrobot.mismarketing.pojo.enroll.User;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by GYQ on 2018/1/9.
 */
public interface UserDao {

    Integer addUser(User user) throws SQLException;

    Integer getCountsByUsername(Map<String,String> map)throws SQLException;

    User loginUser(User user)throws SQLException;

}
