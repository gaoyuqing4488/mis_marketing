package com.misrobot.mismarketing.dao.enroll;

import com.misrobot.mismarketing.pojo.enroll.Enroll;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by GYQ on 2018/1/9.
 */
public interface EnrollDao {

    public  Integer addEnRoll(Enroll enroll)throws SQLException;

    public Integer getCountByUserid(Map<String,Integer> map)throws SQLException;
}
