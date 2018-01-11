package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.pojo.BaseRequest;

/**
 * Created by GYQ on 2017/7/31.
 */
public class MismarketingAdminUserReq extends BaseRequest {
    private Long userid;

    private Integer requestpage;

    private Integer sizeperpage;

    public Integer getRequestpage() {
        return requestpage;
    }

    public void setRequestpage(Integer requestpage) {
        this.requestpage = requestpage;
    }

    public Integer getSizeperpage() {
        return sizeperpage;
    }

    public void setSizeperpage(Integer sizeperpage) {
        this.sizeperpage = sizeperpage;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}

