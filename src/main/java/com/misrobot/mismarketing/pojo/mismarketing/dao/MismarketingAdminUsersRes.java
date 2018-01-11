package com.misrobot.mismarketing.pojo.mismarketing.dao;


import com.misrobot.mismarketing.constants.MismarketingUserType;
import com.misrobot.mismarketing.pojo.BaseResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 用户管理列表
 * Created by gao on 2017/5/11.
 */
public class MismarketingAdminUsersRes extends BaseResponse {

    private List<MismarketingUserAdmin> mismarketingUserAdmins;

    private Integer counts;

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public List<MismarketingUserAdmin> getMismarketingUserAdmins() {
        return mismarketingUserAdmins;
    }

    public void setMismarketingUserAdmins(List<MismarketingUserAdmin> mismarketingUserAdmins) {
        this.mismarketingUserAdmins = mismarketingUserAdmins;
    }

    public static class MismarketingUserAdmin{
        private String  username;
        private Long userid;
        private String loginname;
        private String type;
        private Integer activatedays;
        private String districtName;
        private Integer districtid;
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

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public Integer getDistrictid() {
            return districtid;
        }

        public void setDistrictid(Integer districtid) {
            this.districtid = districtid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getActivatedays() {
            return activatedays;
        }

        public void setActivatedays(Integer activatedays) {
            this.activatedays = activatedays;
        }
        public static MismarketingUserAdmin assign(MismarketingUser mismarketingAdminUser){
            MismarketingUserAdmin yxUserAdmin=new MismarketingUserAdmin();
            yxUserAdmin.setUserid(mismarketingAdminUser.getUserid());
            yxUserAdmin.setLoginname(mismarketingAdminUser.getLoginname());
            if(!StringUtils.isEmpty(mismarketingAdminUser.getUsername())){
                yxUserAdmin.setUsername(mismarketingAdminUser.getUsername());
            }
            if(!StringUtils.isEmpty(mismarketingAdminUser.getDistrictName())){
                yxUserAdmin.setDistrictName(mismarketingAdminUser.getDistrictName());
            }
            if(!StringUtils.isEmpty(mismarketingAdminUser.getProvincename())){
                yxUserAdmin.setProvincename(mismarketingAdminUser.getProvincename());
            }
            if(mismarketingAdminUser.getProvinceid()!=null){
                yxUserAdmin.setProvinceid(mismarketingAdminUser.getProvinceid());
            }
            if(mismarketingAdminUser.getDistrictid()!=null){
                yxUserAdmin.setDistrictid(mismarketingAdminUser.getDistrictid());
            }
            if(mismarketingAdminUser.getType()== MismarketingUserType.MANAGE.getValue()){
                yxUserAdmin.setType(MismarketingUserType.MANAGE.getDesc());
            }else{
                yxUserAdmin.setType(MismarketingUserType.COMMON.getDesc());
            }
            long expireTime = mismarketingAdminUser.getExpiretime().getTime();
            long nowTtime = new Date().getTime();
            long duration = expireTime-nowTtime;
            double days =((duration)/1000.0/60/60/24);
            yxUserAdmin.setActivatedays((int)days);
            return yxUserAdmin;
        }
    }


}

