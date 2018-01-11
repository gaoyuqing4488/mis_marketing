package com.misrobot.mismarketing.pojo.mismarketing.dao;

/**
 * 销售人员地区信息
 * Created by GYQ on 2017/8/4.
 */
public class UserDistrictInfo {
    private Long userid;
    private String districtName;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
