package com.misrobot.mismarketing.dao.weekly;

import com.misrobot.mismarketing.pojo.weekly.WeeklyInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by GYQ on 2017/8/6.
 */
public interface WeeklyInfoDao {
    /*批量添加*/
    public Integer addWeeklyInfo(List<WeeklyInfo> list) throws SQLException;

    public void updateWeeklyInfo(WeeklyInfo weeklyInfo) throws SQLException;

    public void updateWeeklyInfoList(List<WeeklyInfo> list) throws SQLException;

    public void deleteWeeklyInfo(Map<String, Integer> map) throws SQLException;

    public void deleteWeeklyInfos(List<WeeklyInfo> list) throws SQLException;
}
