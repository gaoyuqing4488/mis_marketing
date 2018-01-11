package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.pojo.BaseRequest;

/**
 * Created by GYQ on 2017/7/31.
 */
public class QueryUserReq extends BaseRequest {
    private Long userid;
    private Long loginuserid;

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
}

