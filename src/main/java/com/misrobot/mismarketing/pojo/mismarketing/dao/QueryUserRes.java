package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.pojo.BaseResponse;

/**
 * Created by GYQ on 2017/7/31.
 */
public class QueryUserRes extends BaseResponse {
    private String username;
    private String loginname;
    private String pwd;
    private String districtName;
    private Integer districtid;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public static QueryUserRes assign(MismarketingUser mismarketingAdminUser){
        QueryUserRes queryUserRes=new QueryUserRes();
        queryUserRes.setLoginname(mismarketingAdminUser.getLoginname());
        queryUserRes.setUsername(mismarketingAdminUser.getUsername());
        queryUserRes.setPwd(mismarketingAdminUser.getPwd());
        queryUserRes.setDistrictid(mismarketingAdminUser.getDistrictid());
        queryUserRes.setDistrictName(mismarketingAdminUser.getDistrictName());
        return queryUserRes;
    }
}

