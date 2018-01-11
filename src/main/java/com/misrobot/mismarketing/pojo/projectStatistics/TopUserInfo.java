package com.misrobot.mismarketing.pojo.projectStatistics;

/**
 * Created by GYQ on 2017/8/8.
 */
public class TopUserInfo {
    private Long id;
    private String username;
    private String districtName;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
