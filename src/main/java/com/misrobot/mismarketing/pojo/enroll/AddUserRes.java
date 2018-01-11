package com.misrobot.mismarketing.pojo.enroll;

import com.misrobot.mismarketing.pojo.BaseResponse;

/**
 * Created by GYQ on 2018/1/9.
 */
public class AddUserRes extends BaseResponse {
    private Integer userid;

    private Integer status;

    private String username;

    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
