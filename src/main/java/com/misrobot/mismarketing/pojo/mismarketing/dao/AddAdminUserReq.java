package com.misrobot.mismarketing.pojo.mismarketing.dao;


import com.misrobot.mismarketing.pojo.BaseRequest;

/**
 * 添加管理员所需的参数
 * Created by gao on 2017/5/12.
 */
public class AddAdminUserReq extends BaseRequest {
    private Long loginuserid;
    private String username;
    private String pwd;
    private String loginname;
    private Integer type;
    private Integer districtid;
    private String districtname;

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public Long getLoginuserid() {
        return loginuserid;
    }

    public void setLoginuserid(Long loginuserid) {
        this.loginuserid = loginuserid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

