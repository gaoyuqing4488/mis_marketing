package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.pojo.BaseRequest;

/**
 * Created by GYQ on 2017/7/31.
 */
public class UpdateAdminUserReq extends BaseRequest {
    private Long loginuserid;
    private Long userid;
    private String username;
    private Integer type;
    private String pwd;
    private  String loginname;
    private String isupdate;//是否修改 0表示是 1表示否
    private Integer districtid;

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public String getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(String isupdate) {
        this.isupdate = isupdate;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public Long getLoginuserid() {
        return loginuserid;
    }

    public void setLoginuserid(Long loginuserid) {
        this.loginuserid = loginuserid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

