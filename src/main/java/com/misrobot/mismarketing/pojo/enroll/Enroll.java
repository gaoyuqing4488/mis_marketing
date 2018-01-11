package com.misrobot.mismarketing.pojo.enroll;

import com.misrobot.mismarketing.pojo.BaseRequest;

import java.util.Date;

/**
 * 报名表
 * Created by GYQ on 2018/1/9.
 */
public class Enroll extends BaseRequest {
    private Integer id;

    private String username;

    private String schoolname;

    private String classname;

    private String major;

    private String telephone;

    private String mail;

    private Integer areaid;

    private String contestitem;

    private Date createtime;

    private Integer userid;

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

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getContestitem() {
        return contestitem;
    }

    public void setContestitem(String contestitem) {
        this.contestitem = contestitem;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
