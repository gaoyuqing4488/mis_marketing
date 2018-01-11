package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.constants.EnrollStatus;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.dao.enroll.EnrollDao;
import com.misrobot.mismarketing.dao.enroll.UserDao;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.enroll.AddUserRes;
import com.misrobot.mismarketing.pojo.enroll.User;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.spec.EncodedKeySpec;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GYQ on 2018/1/9.
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private EnrollDao enrollDao;

    @Override
    public AddUserRes addUser(User user) throws RestException {
        AddUserRes addUserRes = new AddUserRes();
        String username = user.getUsername();
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        try {
            int count = userDao.getCountsByUsername(map);
            if (count > 0) {
                addUserRes.setErrcode(ErrorCode.LOGIN_NAME_EXIST);
                addUserRes.setErrdesc(ErrorCode.LOGIN_NAME_EXIST);
            }
            Integer result = userDao.addUser(user);
            if (result > 0) {
                addUserRes.setErrcode(Constants.ERRCODE_SUCCESS + "");
                addUserRes.setUserid(user.getId());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return addUserRes;
    }

    @Override
    public AddUserRes loginUserInfo(User user, HttpSession session) throws RestException {
        AddUserRes addUserRes = new AddUserRes();
        String username = user.getUsername();
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        try {
            Integer count = userDao.getCountsByUsername(map);
            if (count < 1) {
                addUserRes.setErrcode(ErrorCode.ADMIN_USER_NOT_EXIST + "");
                addUserRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.ADMIN_USER_NOT_EXIST));
            }
            User userInfo = userDao.loginUser(user);
            if (count > 0 && userInfo == null) {
                addUserRes.setErrcode(ErrorCode.PWD_ERROR + "");
                addUserRes.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.PWD_ERROR));
            }
            if (userInfo != null) {
                String token = TokenUtil.getUserToken(Long.valueOf(userInfo.getId()), session);
                addUserRes.setErrcode(Constants.ERRCODE_SUCCESS + "");
                addUserRes.setUserid(userInfo.getId());

                Map<String, Integer> userMap = new HashMap<String, Integer>();
                userMap.put("userid", userInfo.getId());
                Integer counts = enrollDao.getCountByUserid(userMap);
                if (counts > 0) {
                    addUserRes.setStatus(EnrollStatus.HASENROLL.getValue());
                }
                addUserRes.setStatus(EnrollStatus.NOTENROLL.getValue());
                addUserRes.setUsername(userInfo.getUsername());
                addUserRes.setNickname(userInfo.getNickname());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }

        return addUserRes;
    }
}
