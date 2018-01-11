package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.pojo.BaseResponse;

import java.util.Date;

/**
 * 营销系统pad端登陆成功返回的数据
 * Created by gao on 2017/5/11.
 */
public class MismarketingAdminUserPadRes extends BaseResponse {
    private String username;
    private Date logintime;
    private Long userid;
    private String topinfo;
    private String isfirst;
    private String token;
    private Integer type;//是否管理员

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIsfirst() {
        return isfirst;
    }

    public void setIsfirst(String isfirst) {
        this.isfirst = isfirst;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getTopinfo() {
        return topinfo;
    }

    public void setTopinfo(String topinfo) {
        this.topinfo = topinfo;
    }
}

