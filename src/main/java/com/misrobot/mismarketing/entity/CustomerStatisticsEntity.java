package com.misrobot.mismarketing.entity;

import com.misrobot.mismarketing.pojo.BasePojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by CHJ on 2017/8/7.
 */
public class CustomerStatisticsEntity extends BasePojo {
    private int id;

    private int customerid;

    private int projectid;

    private BigDecimal money;

    private int year_;

    private int month_;

    private int userid;

    private int status = 0;

    private Timestamp createtime;

    public int getId() {
        return id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public int getYear_() {
        return year_;
    }

    public int getMonth_() {
        return month_;
    }

    public int getUserid() {
        return userid;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setYear_(int year_) {
        this.year_ = year_;
    }

    public void setMonth_(int month_) {
        this.month_ = month_;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }
}
