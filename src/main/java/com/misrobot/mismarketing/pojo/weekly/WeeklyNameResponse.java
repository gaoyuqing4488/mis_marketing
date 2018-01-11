package com.misrobot.mismarketing.pojo.weekly;

import com.misrobot.mismarketing.pojo.BaseResponse;

import java.util.List;

/**
 * 周报名称
 * Created by GYQ on 2017/8/4.
 */
public class WeeklyNameResponse extends BaseResponse {

    private String districtName;

    private String weeklyName;

    private String userName;

    private String date;

    private List<WeeklyProject> weeklyProjects;

    public List<WeeklyProject> getWeeklyProjects() {
        return weeklyProjects;
    }

    public void setWeeklyProjects(List<WeeklyProject> weeklyProjects) {
        this.weeklyProjects = weeklyProjects;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getWeeklyName() {
        return weeklyName;
    }

    public void setWeeklyName(String weeklyName) {
        this.weeklyName = weeklyName;
    }
}
