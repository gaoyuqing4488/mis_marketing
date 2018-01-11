package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.projectStatistics.CompanyStatisticsInfo;
import com.misrobot.mismarketing.pojo.projectStatistics.MyCompanyStatisticsInfo;
import com.misrobot.mismarketing.pojo.projectStatistics.MyCustomerProjectInfo;
import com.misrobot.mismarketing.pojo.projectStatistics.MyCustomerProjectStatistics;

import javax.servlet.http.HttpSession;

/**
 * Created by GYQ on 2017/8/9.
 */
public interface CompanyStatisticsService {

    /*获取公司报告统计*/
    public CompanyStatisticsInfo getCompanyStatisticsInfo(Long loginid, HttpSession session) throws RestException;

    /*获取我的销售统计*/
    public MyCompanyStatisticsInfo getMyStatisticsInfo(Long loginid, HttpSession session) throws RestException;

    /*获取我的客户项目的统计*/
    public MyCustomerProjectInfo getMyCustomerProjectInfo(Long loginid, Integer type) throws RestException;
}
