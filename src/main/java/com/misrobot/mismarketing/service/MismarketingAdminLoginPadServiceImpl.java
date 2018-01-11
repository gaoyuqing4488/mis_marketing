package com.misrobot.mismarketing.service;


import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.constants.IsFirst;
import com.misrobot.mismarketing.dao.mismarketing.MismarketingUserDao;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.mismarketing.dao.*;

import com.misrobot.mismarketing.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

/**
 * Created by gao on 2017/5/11.
 */
@Service("yxAdminLoginPadService")
public class MismarketingAdminLoginPadServiceImpl extends AbstractService implements MismarketingAdminLoginPadService {
    private static final Logger log = LoggerFactory.getLogger(MismarketingAdminLoginPadServiceImpl.class);
    private static final String FILENAME = "/SystemFile.zip";

    @Autowired
    private MismarketingUserDao mismarketingAdminUserDao;

    @Autowired
    private MismarketingAdminUserService mismarketingAdminUserService;

    /**
     * pad用户登陆返回信息
     *
     * @param adminUserLoginReq
     * @return
     * @throws RestException
     */
    @Override
    public MismarketingAdminUserPadRes getLoginInfo(AdminUserLoginReq adminUserLoginReq, HttpSession session) throws RestException {
        MismarketingAdminUserPadRes mismarketingAdminUserPadRes = new MismarketingAdminUserPadRes();
        try {
            if (StringUtils.isEmpty(adminUserLoginReq.getLoginname()) || StringUtils.isEmpty(adminUserLoginReq.getPwd()) || StringUtils.isEmpty(adminUserLoginReq.getCommand()))
                throw new RestException(ErrorCode.REQUEST_PARAMETER_EXCEPTION, ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
            mismarketingAdminUserPadRes.setCommand(adminUserLoginReq.getCommand());
            String loginname = adminUserLoginReq.getLoginname();
//            YxAdminU mismarketingAdminUser=mismarketingAdminUserDao.getLoginInfo(loginname);
            MismarketingUser mismarketingAdminUser = mismarketingAdminUserDao.getLoginInfo(loginname);

            if (mismarketingAdminUser == null) {
                //throw new RestException(ErrorCode.USER_NOT_EXIST, ErrorMessageUtil.getMessage(ErrorCode.USER_NOT_EXIST));
                mismarketingAdminUserPadRes.setErrcode(ErrorCode.ADMIN_USER_NOT_EXIST);
                mismarketingAdminUserPadRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.ADMIN_USER_NOT_EXIST));
                return mismarketingAdminUserPadRes;
            }
            String pwd = adminUserLoginReq.getPwd();
            Long userid = mismarketingAdminUser.getUserid();

            if (!(PasswordTool.getPwdEncode(pwd, mismarketingAdminUser.getSalt()).equals(mismarketingAdminUser.getPwd()))) {
                // throw new RestException(ErrorCode.LOGIN_FAILED, ErrorMessageUtil.getMessage(ErrorCode.LOGIN_FAILED));
                mismarketingAdminUserPadRes.setErrcode(ErrorCode.LOGIN_FAILED);
                mismarketingAdminUserPadRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.LOGIN_FAILED));
                return mismarketingAdminUserPadRes;
            }
            //查询是否到期
            if (mismarketingAdminUser.getExpiretime().getTime() < new Date().getTime()) {
                mismarketingAdminUserDao.updateUserStatus(mismarketingAdminUser.getUserid());
                mismarketingAdminUserPadRes.setErrcode(ErrorCode.USER_EXPIRE);
                mismarketingAdminUserPadRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.USER_EXPIRE));
//            throw new RestException(ErrorCode.LOGIN_FAILED, ErrorMessageUtil.getMessage(ErrorCode.LOGIN_FAILED));
                return mismarketingAdminUserPadRes;
            }
            //后台非管理者
//            if (!StringUtils.isEmpty(adminUserLoginReq.getType()) && adminUserLoginReq.getType() == 0) {
//                if (mismarketingAdminUser.getType() != MismarketingUserType.MANAGE.getValue()) {
//                    mismarketingAdminUserPadRes.setErrcode(ErrorCode.NOT_USER_MANAGE);
//                    mismarketingAdminUserPadRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.NOT_USER_MANAGE));
////            throw new RestException(ErrorCode.LOGIN_FAILED, ErrorMessageUtil.getMessage(ErrorCode.LOGIN_FAILED));
//                    return mismarketingAdminUserPadRes;
//                }
//
//                String token = TokenUtil.getUserToken(userid, session);
//                mismarketingAdminUserPadRes.setToken(token);
//            }
            mismarketingAdminUserPadRes.setUsername(mismarketingAdminUser.getUsername());
            if (!StringUtils.isEmpty(mismarketingAdminUser.getLogintime())) {
                mismarketingAdminUserPadRes.setLogintime(mismarketingAdminUser.getLogintime());
            }
            int days = (int) (Math.round((mismarketingAdminUser.getExpiretime().getTime() - new Date().getTime()) / 1000.0 / 60 / 60 / 24));
            if (StringUtils.isEmpty(adminUserLoginReq.getType())) {
                mismarketingAdminUserPadRes.setTopinfo(days + "");
            }
            mismarketingAdminUserPadRes.setUserid(mismarketingAdminUser.getUserid());
            mismarketingAdminUserPadRes.setType(mismarketingAdminUser.getType());
//            if (StringUtils.isEmpty(mismarketingAdminUser.getLogintime())) {
//                mismarketingAdminUserPadRes.setIsfirst(IsFirst.YES.getDesc());
//            } else {
//                mismarketingAdminUserPadRes.setIsfirst(IsFirst.NO.getDesc());
//            }
            if (mismarketingAdminUser.getIsupdate() == IsFirst.NO.getValue()) {
                mismarketingAdminUserPadRes.setIsfirst(IsFirst.YES.getDesc());
            } else {
                mismarketingAdminUserPadRes.setIsfirst(IsFirst.NO.getDesc());
            }
            String path = AppUtil.getMessage("login_log_path");
            File logDir = new File(path);
            if (!logDir.exists()) logDir.mkdirs();
            File logFile = new File(path + File.separator + "log.txt");
            try {
                FileOutputStream txtfile = new FileOutputStream(logFile, true);
                PrintStream objectOutputStream = new PrintStream(txtfile, true, "UTF-8");
                objectOutputStream.println("登陆日志：" + mismarketingAdminUserPadRes.getUsername() + "在" + new Date() + "登陆成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
            mismarketingAdminUserDao.updateUserLogin(mismarketingAdminUser.getUserid());
//            txtfile.close();
//            objectOutputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        mismarketingAdminUserPadRes.setErrcode("0");
        return mismarketingAdminUserPadRes;
    }

    @Override
    public QueryFileSizeRes getLargeFileSize(String filename) {
        QueryFileSizeRes queryFileSizeRes = new QueryFileSizeRes();
        // String path="";
        String path = AppUtil.getMessage("file_path") + "/";
        File pathFile = new File(path);
        if (!pathFile.exists()) pathFile.mkdirs();
        path += filename;
        //String path = SpringWebTool.getRealPath("/file/"+filename);

        System.out.print("++++++++++++++++++++++" + path);
        if (StringUtils.isEmpty(path)) {
            queryFileSizeRes.setFileSize(-1l);
            return queryFileSizeRes;
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            queryFileSizeRes.setFileSize(-1l);
            return queryFileSizeRes;

        }
        if (raf != null) {
            long ret = -1;
            try {
                ret = raf.length();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                queryFileSizeRes.setFileSize(ret);
                return queryFileSizeRes;
            }
        } else {
            queryFileSizeRes.setFileSize(-1l);
            return queryFileSizeRes;
        }

    }

    @Override
    public QueryFileRes getFilePath() throws RestException {
        QueryFileRes queryFileRes = new QueryFileRes();
        String ipAddr = SpringWebTool.getRequest().getLocalAddr();
        int port = SpringWebTool.getRequest().getServerPort();
        queryFileRes.setFilepath("http://" + ipAddr + ":" + port + "/misrobot-system" + "/file" + FILENAME);
        //queryFileRes.setFilepath("http://" + ipAddr + ":" + port + "/file" + FILENAME);
        System.out.print("+++++++++++++++++++++++++++++++" + queryFileRes.getFilepath());
        return queryFileRes;
    }

    @Override
    public MismarketingAdminUserPadRes getLoginInfos(AdminUserLoginReq adminUserLoginReq, HttpSession session) throws RestException {
        MismarketingAdminUserPadRes mismarketingAdminUserPadRes = new MismarketingAdminUserPadRes();
        try {
            if (StringUtils.isEmpty(adminUserLoginReq.getLoginname()) || StringUtils.isEmpty(adminUserLoginReq.getPwd()) || StringUtils.isEmpty(adminUserLoginReq.getCommand()))
                throw new RestException(ErrorCode.REQUEST_PARAMETER_EXCEPTION, ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
            mismarketingAdminUserPadRes.setCommand(adminUserLoginReq.getCommand());
            String loginname = adminUserLoginReq.getLoginname();
//            YxAdminU mismarketingAdminUser=mismarketingAdminUserDao.getLoginInfo(loginname);
            MismarketingUser mismarketingAdminUser = mismarketingAdminUserDao.getLoginInfo(loginname);

            if (mismarketingAdminUser == null) {
                //throw new RestException(ErrorCode.USER_NOT_EXIST, ErrorMessageUtil.getMessage(ErrorCode.USER_NOT_EXIST));
                mismarketingAdminUserPadRes.setErrcode(ErrorCode.ADMIN_USER_NOT_EXIST);
                mismarketingAdminUserPadRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.ADMIN_USER_NOT_EXIST));
                return mismarketingAdminUserPadRes;
            }
            String pwd = adminUserLoginReq.getPwd();
            Long userid = mismarketingAdminUser.getUserid();

            if (!(PasswordTool.getPwdEncode(pwd, mismarketingAdminUser.getSalt()).equals(mismarketingAdminUser.getPwd()))) {
                // throw new RestException(ErrorCode.LOGIN_FAILED, ErrorMessageUtil.getMessage(ErrorCode.LOGIN_FAILED));
                mismarketingAdminUserPadRes.setErrcode(ErrorCode.LOGIN_FAILED);
                mismarketingAdminUserPadRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.LOGIN_FAILED));
                return mismarketingAdminUserPadRes;
            }
            //查询是否到期
            if (mismarketingAdminUser.getExpiretime().getTime() < new Date().getTime()) {
                mismarketingAdminUserDao.updateUserStatus(mismarketingAdminUser.getUserid());
                mismarketingAdminUserPadRes.setErrcode(ErrorCode.USER_EXPIRE);
                mismarketingAdminUserPadRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.USER_EXPIRE));
//            throw new RestException(ErrorCode.LOGIN_FAILED, ErrorMessageUtil.getMessage(ErrorCode.LOGIN_FAILED));
                return mismarketingAdminUserPadRes;
            }
            //后台非管理者
            if (!StringUtils.isEmpty(adminUserLoginReq.getType()) && adminUserLoginReq.getType() == 0) {
//                if (mismarketingAdminUser.getType() != MismarketingUserType.MANAGE.getValue()) {
//                    mismarketingAdminUserPadRes.setErrcode(ErrorCode.NOT_USER_MANAGE);
//                    mismarketingAdminUserPadRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.NOT_USER_MANAGE));
////            throw new RestException(ErrorCode.LOGIN_FAILED, ErrorMessageUtil.getMessage(ErrorCode.LOGIN_FAILED));
//                    return mismarketingAdminUserPadRes;
//                }
                mismarketingAdminUserPadRes.setType(mismarketingAdminUser.getType());
                String token = TokenUtil.getUserToken(userid, session);
                mismarketingAdminUserPadRes.setToken(token);
            }
            mismarketingAdminUserPadRes.setUsername(mismarketingAdminUser.getUsername());
            if (!StringUtils.isEmpty(mismarketingAdminUser.getLogintime())) {
                mismarketingAdminUserPadRes.setLogintime(mismarketingAdminUser.getLogintime());
            }
            int days = (int) (Math.round((mismarketingAdminUser.getExpiretime().getTime() - new Date().getTime()) / 1000.0 / 60 / 60 / 24));
            if (StringUtils.isEmpty(adminUserLoginReq.getType())) {
                mismarketingAdminUserPadRes.setTopinfo(days + "");
            }
            mismarketingAdminUserPadRes.setUserid(mismarketingAdminUser.getUserid());
//            if (StringUtils.isEmpty(mismarketingAdminUser.getLogintime())) {
//                mismarketingAdminUserPadRes.setIsfirst(IsFirst.YES.getDesc());
//            } else {
//                mismarketingAdminUserPadRes.setIsfirst(IsFirst.NO.getDesc());
//            }
            if (mismarketingAdminUser.getIsupdate() == IsFirst.NO.getValue()) {
                mismarketingAdminUserPadRes.setIsfirst(IsFirst.YES.getDesc());
            } else {
                mismarketingAdminUserPadRes.setIsfirst(IsFirst.NO.getDesc());
            }
            mismarketingAdminUserDao.updateUserLogin(mismarketingAdminUser.getUserid());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        mismarketingAdminUserPadRes.setErrcode("0");
        return mismarketingAdminUserPadRes;
    }
}

