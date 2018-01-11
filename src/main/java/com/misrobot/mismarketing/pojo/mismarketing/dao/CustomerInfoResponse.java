package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.entity.CustomerDetailEntity;
import com.misrobot.mismarketing.entity.CustomerEntity;
import com.misrobot.mismarketing.pojo.BaseResponse;
import com.misrobot.mismarketing.util.JsonUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHJ on 2017/8/5.
 */
public class CustomerInfoResponse extends BaseResponse {

    public ArrayList<CustomerInfoUnit> customerlist;

    public ArrayList<CustomerInfoUnit> getCustomerlist() {
        return customerlist;
    }

    public void setCustomerlist(ArrayList<CustomerInfoUnit> customerlist) {
        this.customerlist = customerlist;
    }

    public static class CustomerInfoUnit {
        private int customerid = 0;

        private String fullname = ""; //客户名称

        private int projectnum = 1; //客户项目数量

        private String budget = ""; //总体预算

        private String budgetsale_1 = ""; //预计销售额—1

        private String budgetsale_2 = ""; //预计销售额—2

        private String budgetsale_3 = ""; //预计销售额—3

        private String actualsale = ""; //实际销售额

        private int customertype = 0; //客户类别

        private int customerrate = 0; //客户级别

        private String customerratedescribe = ""; //客户级别说明

        private int customernum = 1; //人数

        private int ishaveskillcenter = 0; //是否有技能中心

        private int skillcenterarea = 2; //技能中心面积

        private int skillcenteradministernum = 2; //技能中心管理员数量

        private int customerownerid = 1; //客户所有人ID

        private String customerowner = ""; //客户所有人

        private String province = ""; //所属省份

        private String city = ""; //所属城市

        private String simplyname = ""; //客户简称

        private int administrativeattribution = 0; //行政归属

        private int schooltype = 0; //学校类型

        private int hospitalrate = 0; //医院级别

        private int isattachhospital = 0; //是否是大学附属医院

        private String isattachhospitaldescribe = ""; //是否是大学附属医院说明

        private int isteachhospital = 0; //是否是教学医院

        private List<CustomerDetailEntity> customerdetailinfolist = new ArrayList<>(); //用户详细信息

        public int getCustomerid() {
            return customerid;
        }

        public String getFullname() {
            return fullname;
        }

        public int getProjectnum() {
            return projectnum;
        }

        public String getBudget() {
            return budget;
        }

        public String getBudgetsale_1() {
            return budgetsale_1;
        }

        public String getBudgetsale_2() {
            return budgetsale_2;
        }

        public String getBudgetsale_3() {
            return budgetsale_3;
        }

        public String getActualsale() {
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

        public List<CustomerDetailEntity> getCustomerdetailinfolist() {
            return customerdetailinfolist;
        }

        public void setCustomerid(int id) {
            this.customerid = id;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public void setProjectnum(int projectnum) {
            this.projectnum = projectnum;
        }

        public void setBudget(String budget) {
            this.budget = budget;
        }

        public void setBudgetsale_1(String budgetsale_1) {
            this.budgetsale_1 = budgetsale_1;
        }

        public void setBudgetsale_2(String budgetsale_2) {
            this.budgetsale_2 = budgetsale_2;
        }

        public void setBudgetsale_3(String budgetsale_3) {
            this.budgetsale_3 = budgetsale_3;
        }

        public void setActualsale(String actualsale) {
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

        public void setCustomerdetailinfolist(List<CustomerDetailEntity> customerdetailinfolist) {
            this.customerdetailinfolist = customerdetailinfolist;
        }

        public void init(CustomerEntity customerEntity, List<CustomerDetailEntity> customerDetailEntityList) {

            this.customerid = customerEntity.getId();

            this.fullname = customerEntity.getFullname(); //客户名称

            this.projectnum = customerEntity.getProjectnum(); //客户项目数量

            this.budget = JsonUtil.getMoney(customerEntity.getBudget()); //总体预算

            this.budgetsale_1 = JsonUtil.getMoney(customerEntity.getBudgetsale_1()); //预计销售额—1

            this.budgetsale_2 = JsonUtil.getMoney(customerEntity.getBudgetsale_2()); //预计销售额—2

            this.budgetsale_3 = JsonUtil.getMoney(customerEntity.getBudgetsale_3()); //预计销售额—3

            this.actualsale = JsonUtil.getMoney(customerEntity.getActualsale()); //实际销售额

            this.customertype = customerEntity.getCustomertype(); //客户类别

            this.customerrate = customerEntity.getCustomerrate(); //客户级别

            this.customerratedescribe = customerEntity.getCustomerratedescribe(); //客户级别说明

            this.customernum = customerEntity.getCustomernum(); //人数

            this.ishaveskillcenter = customerEntity.getIshaveskillcenter(); //是否有技能中心

            this.skillcenterarea = customerEntity.getSkillcenterarea(); //技能中心面积

            this.skillcenteradministernum = customerEntity.getSkillcenteradministernum(); //技能中心管理员数量

            this.customerownerid = customerEntity.getCustomerownerid(); //客户所有人ID

            this.customerowner = customerEntity.getCustomerowner(); //客户所有人

            this.province = customerEntity.getProvince(); //所属省份

            this.city = customerEntity.getCity(); //所属城市

            this.simplyname = customerEntity.getSimplyname(); //客户简称

            this.administrativeattribution = customerEntity.getAdministrativeattribution(); //行政归属

            this.schooltype = customerEntity.getSchooltype(); // 学校类型

            this.hospitalrate = customerEntity.getHospitalrate(); //医院级别

            this.isattachhospital = customerEntity.getIsattachhospital(); //是否是大学附属医院

            this.isattachhospitaldescribe = customerEntity.getIsattachhospitaldescribe(); //是否是大学附属医院说明

            this.isteachhospital = customerEntity.getIsteachhospital(); //是否是教学医院

            this.customerdetailinfolist = customerDetailEntityList; //用户详细信息
        }
    }
}
