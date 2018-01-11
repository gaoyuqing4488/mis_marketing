package com.misrobot.mismarketing.pojo.weekly;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by GYQ on 2017/9/20.
 */
public class WeeklyUserInfo {

    private String username;

    private Long userid;

    private Date createTime;


    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }



}
