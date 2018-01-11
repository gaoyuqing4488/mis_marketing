package com.misrobot.mismarketing.pojo.mismarketing.dao;
import java.util.Date;

/**
 * Created by gao on 2017/5/15.
 */
public class YxAdminU {
    private Long userid;
    private String loginname;
    private String pwd;
    private String username;
    private int type;//角色类型（0表示管理员 1表示普通用户）
    private int isfirst;
    private int status;//状态（0可用 1不可用 2待激活 ）
    private Date logintime;
    private Date expiretime;//到期时间
    private String salt;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsfirst() {
        return isfirst;
    }

    public void setIsfirst(int isfirst) {
        this.isfirst = isfirst;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }



    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}

