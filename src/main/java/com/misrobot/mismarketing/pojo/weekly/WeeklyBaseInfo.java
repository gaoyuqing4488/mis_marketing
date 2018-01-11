package com.misrobot.mismarketing.pojo.weekly;

import com.misrobot.mismarketing.pojo.district.DistrictInfo;

import java.util.List;

/**
 * Created by GYQ on 2017/9/20.
 */
public class WeeklyBaseInfo {

    private List<WeeklyUserInfo> weeklyUserInfos;

    private List<DistrictInfo> districtInfos;

    public List<WeeklyUserInfo> getWeeklyUserInfos() {
        return weeklyUserInfos;
    }

    public void setWeeklyUserInfos(List<WeeklyUserInfo> weeklyUserInfos) {
        this.weeklyUserInfos = weeklyUserInfos;
    }

    public List<DistrictInfo> getDistrictInfos() {
        return districtInfos;
    }

    public void setDistrictInfos(List<DistrictInfo> districtInfos) {
        this.districtInfos = districtInfos;
    }
}
