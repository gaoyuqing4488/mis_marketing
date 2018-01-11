package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.entity.ProjectEntity;
import com.misrobot.mismarketing.entity.ProjectStatisticsEntity;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.weekly.*;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 周报有关方法
 * Created by GYQ on 2017/8/3.
 */
public interface WeeklyService {

    public AllWeeklyResponse getMyWeeklys(WeeklyListReq weeklyListReq, HttpSession session) throws RestException;

    public AllWeeklyResponse getAllWeeklys(WeeklyListReq weeklyListReq, HttpSession session) throws RestException;

    public WeeklyNameResponse getWeeklyName(Integer type, Map<String, Long> map, Long loginId) throws RestException;

    @Transactional(rollbackFor = Exception.class)
    public Integer addWeekly(AddWeeklyReq addWeeklyReq, HttpSession session) throws RestException;

    public WeeklyInfoResponse getWeeklyInfo(Long loginid, Integer id) throws RestException;

    @Transactional
    public void updateWeeklyInfo(Weekly weekly, List<ProjectEntity> projectEntitys, Long starttime, Long endtime, List<WeeklyInfo> list,List<ProjectStatisticsEntity> projectStatisticsEntities) throws RestException;

    public void deleteWeeklyInfo(Long loginid, Map<String, Integer> map) throws RestException;

    /*获取周报所有人以及办事处*/
    public WeeklyBaseInfo getWeeklyBaseInfos(String username, String name) throws RestException;

}
