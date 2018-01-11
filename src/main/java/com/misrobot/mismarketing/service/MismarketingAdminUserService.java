package com.misrobot.mismarketing.service;


import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.*;
import com.misrobot.mismarketing.pojo.mismarketing.dao.*;
import com.misrobot.mismarketing.pojo.weekly.WeeklyUserInfo;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by gao on 2017/5/11.
 */
public interface MismarketingAdminUserService {

    public MismarketingAdminUsersRes getAlluserInfo(MismarketingAdminUserReq mismarketingAdminUserReq, HttpSession session) throws RestException;

    public QueryUserRes getAdminUserById(QueryUserReq userReq) throws RestException;

    public AddAdminUserRes saveAdminUser(AddAdminUserReq adminUserReq, HttpSession session) throws RestException;

    /*admin修改*/
    public BaseResponse updateAdminUser(UpdateAdminUserReq updateAdminUserReq, HttpSession session) throws RestException;

    /*pad修改*/
    public BaseResponse updateUser(UpdateAdminUserReq updateAdminUserReq, HttpSession session) throws RestException;

    public BaseResponse deleteAdminUser(QueryUserReq queryUserReq, HttpSession session) throws RestException;

    public QueryActivateUserRes getActivateUsers(Map<String, Object> map) throws RestException;

    public void updateActivateUser(List<String> list, String loginuserid, HttpSession session) throws RestException;

    public List<WeeklyUserInfo> getAllUsers(String username)throws RestException;
}

