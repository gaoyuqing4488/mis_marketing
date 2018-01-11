package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.pojo.BaseRequest;

/**
 * Created by GYQ on 2017/7/31.
 */
public class AdminUserLoginReq extends BaseRequest {
    private String loginname;
    private String pwd;
    private Integer type;//admin需要传0

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
}

