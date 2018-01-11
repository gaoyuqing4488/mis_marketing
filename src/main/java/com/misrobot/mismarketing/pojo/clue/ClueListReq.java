package com.misrobot.mismarketing.pojo.clue;

import com.misrobot.mismarketing.pojo.BaseRequest;

import java.util.Date;

/**
 * Created by GYQ on 2018/1/4.
 */
public class ClueListReq extends BaseRequest {
    private Integer selectType;

    private Integer userid;

    private Integer type;

    private Integer requestpage;

    private Integer sizeperpage;

    private String companyName;

    private Integer customerRate;

    private Integer status;

    private Integer level;

    private Integer customerType;

    private Date updateTime;

    public Integer getCustomerRate() {
        return customerRate;
    }

    public void setCustomerRate(Integer customerRate) {
        this.customerRate = customerRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSelectType() {
        return selectType;
    }

    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
