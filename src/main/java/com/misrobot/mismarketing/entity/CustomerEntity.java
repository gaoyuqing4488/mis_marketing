package com.misrobot.mismarketing.entity;

import com.misrobot.mismarketing.pojo.BasePojo;

import java.math.BigDecimal;

public class CustomerEntity extends BasePojo {
    private static final long serialVersionUID = 1L;

    private int id;

    private String fullname = ""; //客户名称

    private int projectnum; //客户项目数量

    private BigDecimal budget = new BigDecimal(0); //总体预算

    private BigDecimal budgetsale_1 = new BigDecimal(0); //预计销售额—1

    private BigDecimal budgetsale_2 = new BigDecimal(0); //预计销售额—2

    private BigDecimal budgetsale_3 = new BigDecimal(0); //预计销售额—3

    private BigDecimal actualsale = new BigDecimal(0); //实际销售额

    private int customertype; //客户类别

    private int customerrate; //客户级别

    private String customerratedescribe = ""; //客户级别说明

    private int customernum; //人数

    private int ishaveskillcenter; //是否有技能中心

    private int skillcenterarea; //技能中心面积

    private int skillcenteradministernum; //技能中心管理员数量

    private int customerownerid; //客户所有人ID

    private String customerowner; //客户所有人

    private String province = ""; //所属省份

    private String city = ""; //所属城市

    private String simplyname = ""; //客户简称

    private int administrativeattribution = 0; //行政归属

    private int schooltype = 0; //学校类型

    private int hospitalrate = 0; //医院级别

    private int isattachhospital = 0; //是否是大学附属医院

    private String isattachhospitaldescribe = ""; //是否是大学附属医院说明

    private int isteachhospital = 0; //是否是教学医院

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public int getProjectnum() {
        return projectnum;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public BigDecimal getBudgetsale_1() {
        return budgetsale_1;
    }

    public BigDecimal getBudgetsale_2() {
        return budgetsale_2;
    }

    public BigDecimal getBudgetsale_3() {
        return budgetsale_3;
    }

    public BigDecimal getActualsale() {
        return actualsale;
    }

    public int getCustomertype() {
        return customertype;
    }

    public int getCustomerrate() {
        return customerrate;
    }

    public String getCustomerratedescribe() {
        return customerratedescribe;
    }

    public int getCustomernum() {
        return customernum;
    }

    public int getIshaveskillcenter() {
        return ishaveskillcenter;
    }

    public int getSkillcenterarea() {
        return skillcenterarea;
    }

    public int getSkillcenteradministernum() {
        return skillcenteradministernum;
    }

    public int getCustomerownerid() {
        return customerownerid;
    }

    public String getCustomerowner() {
        return customerowner;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getSimplyname() {
        return simplyname;
    }

    public int getAdministrativeattribution() {
        return administrativeattribution;
    }

    public int getSchooltype() {
        return schooltype;
    }

    public int getHospitalrate() {
        return hospitalrate;
    }

    public int getIsattachhospital() {
        return isattachhospital;
    }

    public String getIsattachhospitaldescribe() {
        return isattachhospitaldescribe;
    }

    public int getIsteachhospital() {
        return isteachhospital;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setProjectnum(int projectnum) {
        this.projectnum = projectnum;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void setBudgetsale_1(BigDecimal budgetsale_1) {
        this.budgetsale_1 = budgetsale_1;
    }

    public void setBudgetsale_2(BigDecimal budgetsale_2) {
        this.budgetsale_2 = budgetsale_2;
    }

    public void setBudgetsale_3(BigDecimal budgetsale_3) {
        this.budgetsale_3 = budgetsale_3;
    }

    public void setActualsale(BigDecimal actualsale) {
        this.actualsale = actualsale;
    }

    public void setCustomertype(int customertype) {
        this.customertype = customertype;
    }

    public void setCustomerrate(int customerrate) {
        this.customerrate = customerrate;
    }

    public void setCustomerratedescribe(String customerratedescribe) {
        this.customerratedescribe = customerratedescribe;
    }

    public void setCustomernum(int customernum) {
        this.customernum = customernum;
    }

    public void setIshaveskillcenter(int ishaveskillcenter) {
        this.ishaveskillcenter = ishaveskillcenter;
    }

    public void setSkillcenterarea(int skillcenterarea) {
        this.skillcenterarea = skillcenterarea;
    }

    public void setSkillcenteradministernum(int skillcenteradministernum) {
        this.skillcenteradministernum = skillcenteradministernum;
    }

    public void setCustomerownerid(int customerownerid) {
        this.customerownerid = customerownerid;
    }

    public void setCustomerowner(String customerowner) {
        this.customerowner = customerowner;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSimplyname(String simplyname) {
        this.simplyname = simplyname;
    }

    public void setAdministrativeattribution(int administrativeattribution) {
        this.administrativeattribution = administrativeattribution;
    }

    public void setSchooltype(int schooltype) {
        this.schooltype = schooltype;
    }

    public void setHospitalrate(int hospitalrate) {
        this.hospitalrate = hospitalrate;
    }

    public void setIsattachhospital(int isattachhospital) {
        this.isattachhospital = isattachhospital;
    }

    public void setIsattachhospitaldescribe(String isattachhospitaldescribe) {
        this.isattachhospitaldescribe = isattachhospitaldescribe;
    }

    public void setIsteachhospital(int isteachhospital) {
        this.isteachhospital = isteachhospital;
    }
}