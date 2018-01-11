package com.misrobot.mismarketing.dao.weekly;

import com.misrobot.mismarketing.pojo.weekly.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by GYQ on 2017/8/3.
 */
public interface WeeklyDao {
    //获取所有周报列表信息
    public List<AllWeeklys> getAllWeeklys(WeeklyListReq weeklyListReq) throws SQLException;

    public Integer getAllcounts(WeeklyListReq weeklyListReq) throws SQLException;

    //获取上周的最新周报信息
    public List<WeeklyProject> getWeeklyProject(Map<String, Long> map) throws SQLException;

    public Integer addWeekly(Weekly weekly) throws SQLException;

    //单个修改记录
    public void updateWeekly(Weekly weekly) throws SQLException;

    //批量修改周报
    public void updateWeeklyList(List<Weekly> list) throws SQLException;

    //获取某个周报的具体信息
    public List<WeeklyResponse> getWeeklyInfoById(Integer id) throws SQLException;

    public List<WeeklyUserInfo> getAllWeeklyUsers(String username) throws SQLException;

    public String getNewWeeklyCounts(Long userid) throws SQLException;

    public WeeklyUserInfo getWeeklyUserInfo(Integer id) throws SQLException;
}
