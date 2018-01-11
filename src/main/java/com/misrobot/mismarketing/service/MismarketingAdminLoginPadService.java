package com.misrobot.mismarketing.service;


import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.mismarketing.dao.AdminUserLoginReq;
import com.misrobot.mismarketing.pojo.mismarketing.dao.MismarketingAdminUserPadRes;
import com.misrobot.mismarketing.pojo.mismarketing.dao.QueryFileRes;
import com.misrobot.mismarketing.pojo.mismarketing.dao.QueryFileSizeRes;

import javax.servlet.http.HttpSession;

/**
 * Created by gao on 2017/5/11.
 */
public interface MismarketingAdminLoginPadService {

    /**
     * 营销后台管理员登陆
     * @param adminUserLoginReq
     * @return
     * @throws RestException
     */
    public MismarketingAdminUserPadRes getLoginInfo(AdminUserLoginReq adminUserLoginReq,HttpSession session)throws RestException;

    public QueryFileSizeRes getLargeFileSize(String filename) throws RestException;

    public QueryFileRes getFilePath()throws RestException;

    public MismarketingAdminUserPadRes getLoginInfos(AdminUserLoginReq adminUserLoginReq,HttpSession session)throws RestException;
}

