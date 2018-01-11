package com.misrobot.mismarketing.pojo.enroll;

import com.misrobot.mismarketing.pojo.BaseRequest;

import java.util.Date;

/**
 * Created by GYQ on 2018/1/9.
 */
public class User extends BaseRequest {
    private Integer id;

    private String username;

    private String pwd;

    private Date createtime;

    private String  nickname;//昵称

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
