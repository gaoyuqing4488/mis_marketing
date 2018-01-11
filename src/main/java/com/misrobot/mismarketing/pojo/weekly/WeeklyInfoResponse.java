package com.misrobot.mismarketing.pojo.weekly;

import java.util.List;

/**
 * 周报详情所有信息
 * Created by GYQ on 2017/9/21.
 */
public class WeeklyInfoResponse  {

    private List<WeeklyResponse> list;

    private  WeeklyUserInfo weeklyUserInfo;

    public WeeklyUserInfo getWeeklyUserInfo() {
        return weeklyUserInfo;
    }

    public void setWeeklyUserInfo(WeeklyUserInfo weeklyUserInfo) {
        this.weeklyUserInfo = weeklyUserInfo;
    }

    public List<WeeklyResponse> getList() {
        return list;
    }

    public void setList(List<WeeklyResponse> list) {
        this.list = list;
    }
}
