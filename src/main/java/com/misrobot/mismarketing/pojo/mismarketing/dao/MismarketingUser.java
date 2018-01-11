package com.misrobot.mismarketing.pojo.mismarketing.dao;



import com.misrobot.mismarketing.pojo.BasePojo;

import java.util.Date;

/**
 * 营销系统后台用户信息表
 * table（yingxiao_admmin_user）
 * Created by gao on 2017/5/11.
 */
public class MismarketingUser extends BasePojo {
    private static final long serialVersionUID = 1L;
    private Long userid;
    private String loginname;
    private String pwd;
    private String username;
    private int type;//角色类型（0表示管理员 1表示普通用户）
    private int isfirst;
    private int status;//状态（0可用 1不可用 2待激活 ）
    private Date logintime;
    private Date createtime;
    private Date updatetime;
    private Date activatetime;//申请激活时间
    private Date expiretime;//到期时间
    private String salt;
    private int isupdate;//是否正确修改密码
    private Integer districtid;
    private String districtName;
    private Integer provinceid;
    private String provincename;

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }



    public int getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(int isupdate) {
        this.isupdate = isupdate;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getActivatetime() {
        return activatetime;
    }

    public void setActivatetime(Date activatetime) {
        this.activatetime = activatetime;
    }

    public Date getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Date expiretime) {
        this.expiretime = expiretime;
    }
}

