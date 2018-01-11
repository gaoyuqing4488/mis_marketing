package com.misrobot.mismarketing.dao.projectStatistics;

import com.misrobot.mismarketing.pojo.customerStatistics.CustomerStatisticsInfo;
import com.misrobot.mismarketing.pojo.projectStatistics.MyCustomerProjectStatistics;
import com.misrobot.mismarketing.pojo.projectStatistics.TopSalesInfo;
import com.misrobot.mismarketing.pojo.projectStatistics.TopUserInfo;

import java.math.BigDecimal;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by GYQ on 2017/8/8.
 */
public interface ProjectStatisticsDao {
    /*前10名销售员*/
    public List<TopUserInfo> getTopUsers() throws SQLException;

    /*获取当年的总的销售额*/
    public BigDecimal getNewYearStatistics() throws SQLException;

    /*获取每年的项目预计销售额*/
    public List<CustomerStatisticsInfo> getProjectStatisticsByYear() throws SQLException;

    /*获取近2年的每月项目预计销售额*/
    public List<CustomerStatisticsInfo> getProjectStatisticsByMonth() throws SQLException;

    /*获取我的今年销售总额*/
    public BigDecimal getMyNewYearStatistics(Map<String, Long> map);

    /*获取几年最高的销售额*/
    public TopSalesInfo getTopProjectStatistics();

    /*获取我的近3年内的项目销售额*/
    public List<CustomerStatisticsInfo> getMyProjectStatisticsByYear(Map<String, Long> map);

    /*我的近2年内每月销售额*/
    public List<CustomerStatisticsInfo> getMyProjectStatisticsByMonth(Map<String, Long> map);

    /*我的所有客户项目的统计*/
    public MyCustomerProjectStatistics getMyProjectMoney(Map<String, Long> map);

    /*公司所有客户项目的统计*/
    public MyCustomerProjectStatistics getAllProjectMoney();
}
