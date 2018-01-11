package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.constants.MismarketingUserType;

import java.util.Date;
import java.util.List;


/**
 * 待激活的所有用户信息
 * Created by gao on 2017/5/15.
 */
public class QueryActivateUserRes {
    private List<InnerUsers> usersList;
    private Integer counts;

    public List<InnerUsers> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<InnerUsers> usersList) {
        this.usersList = usersList;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public static class InnerUsers{
        //        private String activateTime;
//        private Integer activateDays;
//        private String username;
//        private String begintime;
//        private String endtime;
//        private Integer type;
//        private Integer status;
        private Long userid;
        private String loginname;
        private String username;
        private String type;
        private Integer activateDay;//剩余天数
        private Date expiretime;
        private Integer districtid;
        private String districtname;
        private Integer pid;
        private String pname;

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public Integer getDistrictid() {
            return districtid;
        }

        public void setDistrictid(Integer districtid) {
            this.districtid = districtid;
        }

        public String getDistrictname() {
            return districtname;
        }

        public void setDistrictname(String districtname) {
            this.districtname = districtname;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getActivateDay() {
            return activateDay;
        }

        public void setActivateDay(Integer activateDay) {
            this.activateDay = activateDay;
        }

        public Date getExpiretime() {
            return expiretime;
        }

        public void setExpiretime(Date expiretime) {
            this.expiretime = expiretime;
        }

        public static InnerUsers assign(ActivateUserSql activateUserSql){
            InnerUsers innerUsers=new InnerUsers();
            if(activateUserSql.getType()== MismarketingUserType.COMMON.getValue()){
                innerUsers.setType(MismarketingUserType.COMMON.getDesc());
            }else{
                innerUsers.setType(MismarketingUserType.MANAGE.getDesc());
            }
            innerUsers.setExpiretime(activateUserSql.getExpiretime());
            if(activateUserSql.getActivateDay()<0){
                innerUsers.setActivateDay(0);
            }else{
                innerUsers.setActivateDay(activateUserSql.getActivateDay());
            }
            innerUsers.setUsername(activateUserSql.getUsername());
            innerUsers.setLoginname(activateUserSql.getLoginname());
            innerUsers.setUserid(activateUserSql.getUserid());
            if(activateUserSql.getDistrictid()!=null){
                innerUsers.setDistrictid(activateUserSql.getDistrictid());
                innerUsers.setDistrictname(activateUserSql.getDistrictName());
                innerUsers.setPid(activateUserSql.getPid());
                innerUsers.setPname(activateUserSql.getPname());
            }
            return innerUsers;

        }
    }

}

