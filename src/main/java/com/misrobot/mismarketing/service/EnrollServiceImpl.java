package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.dao.enroll.EnrollDao;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.enroll.Enroll;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GYQ on 2018/1/9.
 */
@Service
public class EnrollServiceImpl extends AbstractService implements EnrollService {
    private static final Logger log = LoggerFactory.getLogger(EnrollServiceImpl.class);

    @Autowired
    private EnrollDao enrollDao;

    @Override
    public Integer addEnroll(Enroll enroll, HttpSession session) throws RestException {
        Integer userId = enroll.getUserid();
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("userid", userId);
        Integer result = 0;
        try {
            if (TokenUtil.checkLoginInfo(Long.valueOf(userId), session)) {
                Integer count = enrollDao.getCountByUserid(map);
                if (count > 0) {
                    result = 2;
                    return result;
                }
                result = enrollDao.addEnRoll(enroll);
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }

        return result;
    }
}
