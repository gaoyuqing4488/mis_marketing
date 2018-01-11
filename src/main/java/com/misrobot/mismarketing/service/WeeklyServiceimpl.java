package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.constants.MismarketingUserType;
import com.misrobot.mismarketing.dao.district.DistrictDao;
import com.misrobot.mismarketing.dao.mismarketing.MismarketingUserDao;
import com.misrobot.mismarketing.dao.project.ProjectEntityMapper;
import com.misrobot.mismarketing.dao.project.ProjectStatisticsEntityMapper;
import com.misrobot.mismarketing.dao.weekly.WeeklyDao;
import com.misrobot.mismarketing.dao.weekly.WeeklyInfoDao;
import com.misrobot.mismarketing.entity.ProjectEntity;
import com.misrobot.mismarketing.entity.ProjectStatisticsEntity;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.district.DistrictInfo;
import com.misrobot.mismarketing.pojo.mismarketing.dao.MismarketingUser;
import com.misrobot.mismarketing.pojo.mismarketing.dao.UserDistrictInfo;
import com.misrobot.mismarketing.pojo.weekly.*;
import com.misrobot.mismarketing.util.DateUtil;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by GYQ on 2017/8/3.
 */
@Service
public class WeeklyServiceimpl extends AbstractService implements WeeklyService {
    private static final Logger log = LoggerFactory.getLogger(WeeklyServiceimpl.class);

    @Autowired
    private WeeklyDao weeklyDao;

    @Autowired
    private MismarketingAdminUserService mismarketingAdminUserService;

    @Autowired
    private MismarketingUserDao mismarketingAdminUserDao;

    @Autowired
    private WeeklyInfoDao weeklyInfoDao;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    ProjectEntityMapper mProjectEntityMapper;

    @Autowired
    ProjectStatisticsEntityMapper projectStatisticsEntityMapper;

    /**
     * 获取我的周报列表
     *
     * @return
     * @throws RestException
     */
    @Override
    public AllWeeklyResponse getMyWeeklys(WeeklyListReq weeklyListReq, HttpSession session) throws RestException {
        AllWeeklyResponse allWeeklyResponse = new AllWeeklyResponse();
        List<AllWeeklys> allWeeklys = new ArrayList<>();
        String loginuserid = weeklyListReq.getLoginid();
        Integer counts = 0;
        if (StringUtils.isEmpty(loginuserid)) {
            throw new RestException(ErrorCode.REQUEST_PARAMETER_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
        }
        try {
            if (TokenUtil.checkLoginInfo(Long.valueOf(loginuserid), session)) {
                weeklyListReq.setUserid(Long.valueOf(loginuserid));
                allWeeklys = weeklyDao.getAllWeeklys(weeklyListReq);
                counts = weeklyDao.getAllcounts(weeklyListReq);
            }
            allWeeklyResponse.setList(allWeeklys);
            allWeeklyResponse.setCounts(counts);
            return allWeeklyResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
    }

    /**
     * 管理者获取所有周报
     *
     * @param weeklyListReq
     * @return
     * @throws RestException
     */
    @Override
    public AllWeeklyResponse getAllWeeklys(WeeklyListReq weeklyListReq, HttpSession session) throws RestException {
        AllWeeklyResponse allWeeklyResponse = new AllWeeklyResponse();
        List<AllWeeklys> allWeeklys = new ArrayList<>();
        String loginuserid = weeklyListReq.getLoginid();
        Integer counts = 0;
        if (StringUtils.isEmpty(loginuserid)) {
            throw new RestException(ErrorCode.REQUEST_PARAMETER_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
        }
        try {
            MismarketingUser mismarketingAdminUser = mismarketingAdminUserDao.getAdminUserById(Long.valueOf(loginuserid));
            int type = mismarketingAdminUser.getType();
            if (TokenUtil.checkLoginInfo(Long.valueOf(loginuserid), session)) {
                if (type != MismarketingUserType.MANAGE.getValue()) {
                    throw new RestException(ErrorCode.NOTQUERY_USER_MANAGE,
                            ErrorMessageUtil.getMessage(ErrorCode.NOTQUERY_USER_MANAGE));
                }
                weeklyListReq.setUserid(null);
                allWeeklys = weeklyDao.getAllWeeklys(weeklyListReq);
                counts = weeklyDao.getAllcounts(weeklyListReq);
            }
            allWeeklyResponse.setList(allWeeklys);
            allWeeklyResponse.setCounts(counts);
            return allWeeklyResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
    }

    /**
     * 获取周报名称
     *
     * @param map
     * @return
     * @throws RestException
     */
    @Override
    public WeeklyNameResponse getWeeklyName(Integer type, Map<String, Long> map, Long loginId) throws RestException {
        WeeklyNameResponse weeklyNameResponse = new WeeklyNameResponse();
        try {
            UserDistrictInfo userDistrictInfo = mismarketingAdminUserDao.getDistrictNameByuid(map);
            if (userDistrictInfo != null) {
                weeklyNameResponse.setDistrictName(userDistrictInfo.getDistrictName());
                weeklyNameResponse.setUserName(userDistrictInfo.getUserName());
            }
            String mondayDate = DateUtil.getDayOfWeek(1);
            String sundayDate = DateUtil.getDayOfWeek(7);
            weeklyNameResponse.setWeeklyName(mondayDate + "—" + sundayDate);
            String name = "【" + weeklyNameResponse.getWeeklyName() + "】 周报 " + userDistrictInfo.getDistrictName() + "办事处";
            String weeklyname = weeklyDao.getNewWeeklyCounts(loginId);
            if ((name).equals(weeklyname)) {
                weeklyNameResponse.setErrcode(ErrorCode.WEEKLY_EXIST);
                weeklyNameResponse.setErrdesc(ErrorMessageUtil.getMessage(ErrorCode.WEEKLY_EXIST));
                return weeklyNameResponse;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String date = simpleDateFormat.format(new Date());
            weeklyNameResponse.setDate(date);
            if (type == 1) {
                List<WeeklyProject> list = weeklyDao.getWeeklyProject(map);
                if (list != null && list.size() > 0) {
                    weeklyNameResponse.setWeeklyProjects(list);
                }
            }
            weeklyNameResponse.setErrcode(Constants.ERRCODE_SUCCESS + "");
            return weeklyNameResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
    }

    @Override
    public Integer addWeekly(AddWeeklyReq addWeeklyReq, HttpSession session) throws RestException {

        Integer result = 0;
        Weekly weekly = new Weekly();
        String name = addWeeklyReq.getName();
        Long user_id = addWeeklyReq.getUser_id();
        BigDecimal sales = addWeeklyReq.getSales();
        Long starttime = addWeeklyReq.getStarttime();
        Long endtime = addWeeklyReq.getEndtime();
        weekly.setUser_id(user_id);
        weekly.setSales(sales);
        weekly.setName(name);
        List<ProjectEntity> projectEntitys = addWeeklyReq.getProjectEntitys();
        try {
            if (TokenUtil.checkLoginInfo(Long.valueOf(user_id), session)) {
                Integer weeklyId = weeklyDao.addWeekly(weekly);
                List<WeeklyInfo> weeklyinfos = addWeeklyReq.getWeekinfos();
                if (weeklyId != null) {
//                    for (WeeklyInfo weeklyInfo : weeklyinfos) {
//                        weeklyInfo.setWeekly_id(weekly.getId());
//                        weeklyInfoDao.addWeeklyInfo(weeklyInfo);
//                    }
                    for (WeeklyInfo weeklyInfo : weeklyinfos) {
                        weeklyInfo.setWeekly_id(weekly.getId());
                    }
                    Integer re = weeklyInfoDao.addWeeklyInfo(weeklyinfos);
                    if (new Date().getTime() >= starttime && new Date().getTime() <= endtime) {
                        Integer total = mProjectEntityMapper.updateProjectInfos(projectEntitys);
                    }

                    return result;
                }
            }
            return 1;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
    }

    @Override
    public WeeklyInfoResponse getWeeklyInfo(Long loginid, Integer id) throws RestException {
        WeeklyInfoResponse weeklyInfoResponse = new WeeklyInfoResponse();
        List<WeeklyResponse> weeklyResponse = new ArrayList<>();
        try {
            // if (mismarketingAdminUserService.checkLogin(Long.valueOf(loginid))) {
            weeklyResponse = weeklyDao.getWeeklyInfoById(id);
            WeeklyUserInfo weeklyUserInfo = weeklyDao.getWeeklyUserInfo(id);
            weeklyInfoResponse.setList(weeklyResponse);
            weeklyInfoResponse.setWeeklyUserInfo(weeklyUserInfo);
            //}
            return weeklyInfoResponse;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
    }

    @Override
    public void updateWeeklyInfo(Weekly weekly, List<ProjectEntity> projectEntitys, Long starttime, Long endtime, List<WeeklyInfo> list, List<ProjectStatisticsEntity> projectStatisticsEntities) throws RestException {
        Long loginid = weekly.getUser_id();
        try {
            // if (mismarketingAdminUserService.checkLogin(Long.valueOf(loginid))) {
            weeklyDao.updateWeekly(weekly);
            weeklyInfoDao.deleteWeeklyInfos(list);
            weeklyInfoDao.addWeeklyInfo(list);
            if (new Date().getTime() >= starttime && new Date().getTime() <= endtime) {
                Integer total = mProjectEntityMapper.updateProjectInfos(projectEntitys);
                projectStatisticsEntityMapper.updateProjectStatisticsByProjectID(projectStatisticsEntities);

            }
            // weeklyInfoDao.updateWeeklyInfoList(list);
            // }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
    }

    @Override
    public void deleteWeeklyInfo(Long loginid, Map<String, Integer> map) throws RestException {
        try {
            //if (mismarketingAdminUserService.checkLogin(Long.valueOf(loginid))) {
            weeklyInfoDao.deleteWeeklyInfo(map);
            // }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
    }

    /**
     * 周报列表需要过滤的基础数据
     *
     * @param username
     * @param name
     * @return
     * @throws RestException
     */
    @Override
    public WeeklyBaseInfo getWeeklyBaseInfos(String username, String name) throws RestException {
        WeeklyBaseInfo weeklyBaseInfos = new WeeklyBaseInfo();
        try {
            Map<String, String> userMap = new HashMap<String, String>();
            userMap.put("username", username);
            Map<String, String> districtMap = new HashMap<String, String>();
            userMap.put("name", name);
            List<WeeklyUserInfo> weeklyUserInfos = mismarketingAdminUserDao.getAllUsers(userMap);
            List<DistrictInfo> districtInfos = districtDao.getAllDistricts(districtMap);
            weeklyBaseInfos.setWeeklyUserInfos(weeklyUserInfos);
            weeklyBaseInfos.setDistrictInfos(districtInfos);

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return weeklyBaseInfos;
    }
}
