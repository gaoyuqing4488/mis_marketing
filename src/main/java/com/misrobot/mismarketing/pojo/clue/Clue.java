package com.misrobot.mismarketing.pojo.clue;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.misrobot.mismarketing.pojo.BasePojo;
import com.misrobot.mismarketing.pojo.BaseRequest;
import sun.rmi.server.InactiveGroupException;

import java.util.Date;

/**
 * 线索表
 * Created by GYQ on 2018/1/3.
 */
public class Clue  extends BaseRequest {
    private Integer id;

    private String companyName;//客户单位名称

    private Integer districtId;

    private String contactName;//客户联系人

    private Integer customerType;//客户类型

    private String department;//业务部

    private String telephone;

    private Integer level;//客户等级

    private Integer customerRate;//接触客户级别

    private Integer customerInitiative;//客户主动性0.客户主动 1.我司主动

    private Integer informationSources;//信息来源（0.会展，1.拜访，2.电话，3.介绍）

    private String clueInfo;//线索信息(线索关闭理由)

    private String progress;//进展

    private Integer createrId;//录入人员

    private Integer managerId;//负责人员的ID

    private Date createTime;

    private Date updateTime;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getCustomerRate() {
        return customerRate;
    }

    public void setCustomerRate(Integer customerRate) {
        this.customerRate = customerRate;
    }

    public Integer getCustomerInitiative() {
        return customerInitiative;
    }

    public void setCustomerInitiative(Integer customerInitiative) {
        this.customerInitiative = customerInitiative;
    }

    public Integer getInformationSources() {
        return informationSources;
    }

    public void setInformationSources(Integer informationSources) {
        this.informationSources = informationSources;
    }

    public String getClueInfo() {
        return clueInfo;
    }

    public void setClueInfo(String clueInfo) {
        this.clueInfo = clueInfo;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}
