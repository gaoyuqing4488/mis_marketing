package com.misrobot.mismarketing.dao.mismarketing;

import com.misrobot.mismarketing.pojo.mismarketing.dao.ActivateUserSql;
import com.misrobot.mismarketing.pojo.mismarketing.dao.MismarketingUser;
import com.misrobot.mismarketing.pojo.mismarketing.dao.UserDistrictInfo;
import com.misrobot.mismarketing.pojo.weekly.WeeklyUserInfo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gao on 2017/5/11.
 */
public interface MismarketingUserDao {

    public MismarketingUser getLoginInfo(String loginname);

    public void updateUserStatus(Long userid) throws SQLException;

    public void deleteUserStatus(Long userid) throws SQLException;

    public List<MismarketingUser> getAlluserInfo(Map<String, Object> map) throws SQLException;

    public MismarketingUser getAdminUserById(Long userid) throws SQLException;

    public void updateUserLogin(Long userid) throws SQLException;

    public void updateFirstLogin(Long userid) throws SQLException;

    public Long addAdminUser(MismarketingUser mismarketingAdminUser) throws SQLException;

    public Integer findUserByLoginName(String loginname) throws SQLException;

    /*后台重置密码*/
    public void updateAdminUser(MismarketingUser mismarketingAdminUser) throws SQLException;

    /*管理员修改自己密码*/
    public void updateUserPWD(MismarketingUser mismarketingAdminUser) throws SQLException;

    /*pad修改密码*/
    public void updatePadUser(MismarketingUser mismarketingAdminUser) throws SQLException;

    /*admin修改密码*/
    public void updateAdminUserPwd(MismarketingUser mismarketingAdminUser) throws SQLException;

    /*过滤筛选待激活的用户*/
    public List<ActivateUserSql> getActivateUser(Map<String, Object> map) throws SQLException;

    public Integer getActivateCounts(Map<String, Object> map) throws SQLException;

    public void updateAdminActivate(List<HashMap<String, Long>> list) throws SQLException;

    /*可用用户的记录数*/
    public Integer getAlluserCounts() throws SQLException;

    /*获取用户所在的办事处*/
    public UserDistrictInfo getDistrictNameByuid(Map<String, Long> map) throws SQLException;

    /*获取所有销售人员的数量*/
    public Integer getSaleCounts() throws SQLException;

    /*获取所有销售人员的信息*/
    public List<WeeklyUserInfo> getAllUsers(Map<String, String> map) throws SQLException;
}
