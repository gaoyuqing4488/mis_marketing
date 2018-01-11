package com.misrobot.mismarketing.service;


import com.misrobot.mismarketing.constants.*;
import com.misrobot.mismarketing.dao.mismarketing.MismarketingUserDao;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.*;
import com.misrobot.mismarketing.pojo.mismarketing.dao.*;
import com.misrobot.mismarketing.pojo.weekly.WeeklyUserInfo;
import com.misrobot.mismarketing.util.*;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by gao on 2017/5/11.
 */
@Service("yxAdminUserService")
public class MismarketingAdminUserServiceImpl extends AbstractService implements MismarketingAdminUserService {
    private static final Logger log = LoggerFactory.getLogger(MismarketingAdminUserServiceImpl.class);

    @Autowired
    private MismarketingUserDao mismarketingUserDao;

    /**
     * 分页获取后台管理信息列表
     *
     * @param mismarketingAdminUserReq
     * @return
     * @throws RestException
     */
    @Override
    public MismarketingAdminUsersRes getAlluserInfo(MismarketingAdminUserReq mismarketingAdminUserReq, HttpSession session) throws RestException {
        MismarketingAdminUsersRes mismarketingAdminUsersRes = new MismarketingAdminUsersRes();
        try {
            MismarketingUser mismarketingAdminUser = mismarketingUserDao.getAdminUserById(mismarketingAdminUserReq.getUserid());

            List<MismarketingAdminUsersRes.MismarketingUserAdmin> yxAdminUsersResList = new ArrayList<>();
            Map<String, Object> map = new HashMap<String, Object>();
            Integer requestpage = mismarketingAdminUserReq.getRequestpage();
            Integer sizeperpage = mismarketingAdminUserReq.getSizeperpage();
            if (mismarketingAdminUser != null) {
                if (StringUtils.isNotEmpty(requestpage + "") && StringUtils.isNotEmpty(sizeperpage + "")) {
                    map.put("requestpage", requestpage);
                    map.put("sizeperpage", sizeperpage);
                }
                List<MismarketingUser> list = mismarketingUserDao.getAlluserInfo(map);
                Integer counts = mismarketingUserDao.getAlluserCounts();
                if (list.size() > 0) {
                    for (MismarketingUser yxUser : list) {
                        MismarketingAdminUsersRes.MismarketingUserAdmin mismarketingUserAdmin = MismarketingAdminUsersRes.MismarketingUserAdmin.assign(yxUser);
                        yxAdminUsersResList.add(mismarketingUserAdmin);
                    }
                    mismarketingAdminUsersRes.setMismarketingUserAdmins(yxAdminUsersResList);
                    mismarketingAdminUsersRes.setCounts(counts);
                }

                mismarketingAdminUsersRes.setCommand(mismarketingAdminUserReq.getCommand());
            } else {
                mismarketingAdminUsersRes.setErrcode(ErrorCode.ADMIN_USER_NOT_EXIST);
                mismarketingAdminUsersRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.ADMIN_USER_NOT_EXIST));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }

        return mismarketingAdminUsersRes;
    }

    /**
     * 获取后台管理员的具体信息
     *
     * @param userReq
     * @return
     * @throws RestException
     */
    @Override
    public QueryUserRes getAdminUserById(QueryUserReq userReq) throws RestException {
        QueryUserRes queryUserRes = new QueryUserRes();
        try {
            Long userid = userReq.getUserid();
            MismarketingUser mismarketingUser = mismarketingUserDao.getAdminUserById(userid);
            queryUserRes = QueryUserRes.assign(mismarketingUser);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return queryUserRes;
    }

    /**
     * 添加管理员
     *
     * @param adminUserReq
     * @return
     * @throws RestException
     */
    @Override
    public AddAdminUserRes saveAdminUser(AddAdminUserReq adminUserReq, HttpSession session) throws RestException {
        AddAdminUserRes addAdminUserRes = new AddAdminUserRes();
        Long loginuserid = adminUserReq.getLoginuserid();
        try {
            if (TokenUtil.checkLoginInfo(loginuserid, session)) {
                Integer counts = mismarketingUserDao.findUserByLoginName(adminUserReq.getLoginname());
                if (counts > 0) {
                    addAdminUserRes.setErrcode(ErrorCode.LOGIN_NAME_EXIST);
                    addAdminUserRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.LOGIN_NAME_EXIST));
                    return addAdminUserRes;
                }
                if (!RegexUtil.checkEmail(adminUserReq.getLoginname()) && !"yufudong".equals(adminUserReq.getLoginname()) && !"allen".equals(adminUserReq.getLoginname())) {
                    addAdminUserRes.setErrcode(ErrorCode.WRONG_EMAIL);
                    addAdminUserRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.WRONG_EMAIL));
                    return addAdminUserRes;
                }
                MismarketingUser mismarketingAdminUser = new MismarketingUser();
                mismarketingAdminUser.setUsername(adminUserReq.getUsername());
                mismarketingAdminUser.setLoginname(adminUserReq.getLoginname());
                mismarketingAdminUser.setExpiretime(DateUtil.getAddDays());
                mismarketingAdminUser.setCreatetime(new Date());
                mismarketingAdminUser.setIsfirst(IsFirst.YES.getValue());
                mismarketingAdminUser.setSalt(PasswordTool.getSale());
                mismarketingAdminUser.setStatus(MismarketingUserStatus.ENABLE.getValue());
                mismarketingAdminUser.setType(adminUserReq.getType());
                mismarketingAdminUser.setDistrictid(adminUserReq.getDistrictid());
                mismarketingAdminUser.setPwd(PasswordTool.getPwdEncode(adminUserReq.getPwd(), mismarketingAdminUser.getSalt()));
                Long userid = mismarketingUserDao.addAdminUser(mismarketingAdminUser);

                if (StringUtils.isEmpty(userid + "")) {
                    addAdminUserRes.setErrcode(ErrorCode.ADD_USER_FAIL);
                    addAdminUserRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.ADD_USER_FAIL));
                    return addAdminUserRes;
                }
                addAdminUserRes.setUserid(userid);
                addAdminUserRes.setCommand(adminUserReq.getCommand());
                addAdminUserRes.setErrcode("0");
            } else {
                addAdminUserRes.setErrcode(ErrorCode.OVERTIME);
                addAdminUserRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.OVERTIME));
                return addAdminUserRes;
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return addAdminUserRes;
    }

    /**
     * 修改管理员信息
     *
     * @param updateAdminUserReq
     * @return
     * @throws RestException
     */
    @Override
    public BaseResponse updateAdminUser(UpdateAdminUserReq updateAdminUserReq, HttpSession session) throws RestException {
        BaseResponse res = new BaseResponse();
        Long loginuserid = updateAdminUserReq.getLoginuserid();
        try {
            if (TokenUtil.checkLoginInfo(loginuserid, session)) {
                Long userid = updateAdminUserReq.getUserid();
                MismarketingUser mismarketingAdminUser = mismarketingUserDao.getAdminUserById(userid);
                String username = updateAdminUserReq.getUsername();
                String pwd = updateAdminUserReq.getPwd();
                Integer type = updateAdminUserReq.getType();
                String loginnname = updateAdminUserReq.getLoginname();
                if (!StringUtils.isEmpty(loginnname)) {
                    if (!loginnname.equals(mismarketingAdminUser.getLoginname())) {
                        Integer counts = mismarketingUserDao.findUserByLoginName(loginnname);
                        if (counts > 0) {
                            res.setErrcode(ErrorCode.LOGIN_NAME_EXIST);
                            res.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.LOGIN_NAME_EXIST));
                            return res;
                        }
                        if (!RegexUtil.checkEmail(loginnname) && !"yufudong".equals(loginnname) && !"allen".equals(loginnname)) {
                            res.setErrcode(ErrorCode.WRONG_EMAIL);
                            res.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.WRONG_EMAIL));
                            return res;
                        }
                    }
                    mismarketingAdminUser.setLoginname(loginnname);
                }
                if (!StringUtils.isEmpty(username)) {
                    mismarketingAdminUser.setUsername(username);
                }
                String salt = mismarketingAdminUser.getSalt();
                if (!StringUtils.isEmpty(pwd)) {
                    mismarketingAdminUser.setPwd(PasswordTool.getPwdEncode(pwd, salt));
                    //mismarketingAdminUser.setIsfirst(IsFirst.YES.getValue());
                }
                if (type != null) {
                    mismarketingAdminUser.setType(type);
                }
                if (updateAdminUserReq.getDistrictid() != null) {
                    mismarketingAdminUser.setDistrictid(updateAdminUserReq.getDistrictid());
                }
                if (loginuserid == userid && type == MismarketingUserType.MANAGE.getValue()) {
                    mismarketingUserDao.updateUserPWD(mismarketingAdminUser);
                } else if (StringUtils.isEmpty(pwd)) {
                    mismarketingUserDao.updateUserPWD(mismarketingAdminUser);
                } else {
                    mismarketingUserDao.updateAdminUser(mismarketingAdminUser);
                }
                res.setCommand(updateAdminUserReq.getCommand());
                res.setErrcode(Constants.SUCCESS);
            } else {
                res.setErrcode(ErrorCode.OVERTIME);
                res.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.OVERTIME));
                return res;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setErrcode(ErrorCode.RABBITMQ_EXCEPTION);
            res.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.RABBITMQ_EXCEPTION));
        }

        return res;
    }

    /**
     * pad修改密码
     *
     * @param updateAdminUserReq
     * @return
     * @throws RestException
     */
    @Override
    public BaseResponse updateUser(UpdateAdminUserReq updateAdminUserReq, HttpSession session) throws RestException {
        BaseResponse res = new BaseResponse();
        Long loginuserid = updateAdminUserReq.getLoginuserid();
        try {
            String isupdate = updateAdminUserReq.getIsupdate();
            Long userid = updateAdminUserReq.getUserid();
            MismarketingUser mismarketingAdminUser = mismarketingUserDao.getAdminUserById(userid);
            String pwd = updateAdminUserReq.getPwd();
            String salt = mismarketingAdminUser.getSalt();
            if (!StringUtils.isEmpty(pwd)) {
                mismarketingAdminUser.setPwd(PasswordTool.getPwdEncode(pwd, salt));
            }
            if (!StringUtils.isEmpty(isupdate) && isupdate.equals("0")) {
                mismarketingUserDao.updatePadUser(mismarketingAdminUser);
            }
            if (!StringUtils.isEmpty(isupdate) && isupdate.equals("2")) {
                mismarketingUserDao.updateAdminUserPwd(mismarketingAdminUser);
            }

            res.setCommand(updateAdminUserReq.getCommand());
            res.setErrcode(Constants.SUCCESS);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setErrcode(ErrorCode.RABBITMQ_EXCEPTION);
            res.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.RABBITMQ_EXCEPTION));
        }

        return res;
    }

    /**
     * 删除后台管理员
     *
     * @param queryUserReq
     * @return
     * @throws RestException
     */
    @Override
    public BaseResponse deleteAdminUser(QueryUserReq queryUserReq, HttpSession session) throws RestException {
        BaseResponse res = new BaseResponse();
        Long loginuserid = queryUserReq.getLoginuserid();
        try {
            if (TokenUtil.checkLoginInfo(loginuserid, session)) {
                Long userid = queryUserReq.getUserid();
                if (loginuserid == userid) {
                    res.setErrcode(ErrorCode.NOT_DELETE_OWN);
                    res.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.NOT_DELETE_OWN));
                    return res;
                }
                mismarketingUserDao.deleteUserStatus(userid);
                res.setCommand(queryUserReq.getCommand());
                res.setErrcode(Constants.SUCCESS);
            } else {
                res.setErrcode(ErrorCode.OVERTIME);
                res.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.OVERTIME));
                return res;
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setErrcode(ErrorCode.RABBITMQ_EXCEPTION);
            res.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.RABBITMQ_EXCEPTION));
        }
        return res;
    }

    /**
     * 得到带激活的用户
     *
     * @param map
     * @return
     * @throws RestException
     */
    @Override
    public QueryActivateUserRes getActivateUsers(Map<String, Object> map) throws RestException {
        QueryActivateUserRes queryActivateUserRes = new QueryActivateUserRes();
        List<QueryActivateUserRes.InnerUsers> list = new ArrayList<>();
        try {
            List<ActivateUserSql> activateUser = mismarketingUserDao.getActivateUser(map);
            Integer counts = mismarketingUserDao.getActivateCounts(map);
            if (activateUser.size() > 0) {
                for (ActivateUserSql activateUserSql : activateUser) {
                    QueryActivateUserRes.InnerUsers info = QueryActivateUserRes.InnerUsers.assign(activateUserSql);
                    list.add(info);
                }
                queryActivateUserRes.setUsersList(list);
            }
            Integer totalcount = counts;
            queryActivateUserRes.setCounts(totalcount);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return queryActivateUserRes;
    }

    /**
     * 批量激活
     *
     * @param list
     * @throws RestException
     */
    @Override
    public void updateActivateUser(List<String> list, String loginuserid, HttpSession session) throws RestException {
        List<HashMap<String, Long>> lists = new ArrayList<HashMap<String, Long>>();
        HashMap<String, Long> map = new HashMap<>();
        try {
            if (TokenUtil.checkLoginInfo(Long.parseLong(loginuserid), session)) {
                if (list.size() > 0) {
                    for (String str : list) {
                        map = new HashMap<String, Long>();
                        map.put("userid", Long.parseLong(str));
                        lists.add(map);
                    }
                }
                mismarketingUserDao.updateAdminActivate(lists);
            } else {
                throw new RestException(ErrorCode.OVERTIME,
                        ErrorMessageUtil.getMessage(ErrorCode.OVERTIME));
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
    }

    @Override
    public List<WeeklyUserInfo> getAllUsers(String username) throws RestException {
        List<WeeklyUserInfo> list=new ArrayList<WeeklyUserInfo>();
        try{
            Map<String,String> nameMap=new HashMap<String,String>();
            if(username==""){
                username=null;
            }
            nameMap.put("username",username);
            list=mismarketingUserDao.getAllUsers(nameMap);
        }catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return list;
    }
}

