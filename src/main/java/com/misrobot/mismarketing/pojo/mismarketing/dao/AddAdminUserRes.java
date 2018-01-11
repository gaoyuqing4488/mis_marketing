package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.pojo.BaseResponse;

/**
 * Created by GYQ on 2017/7/31.
 */
public class AddAdminUserRes extends BaseResponse {
    private Long userid;
    private boolean islogin;

    public boolean islogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}

