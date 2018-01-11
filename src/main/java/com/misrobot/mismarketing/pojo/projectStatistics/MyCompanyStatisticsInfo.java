package com.misrobot.mismarketing.pojo.projectStatistics;

import com.misrobot.mismarketing.pojo.customerProject.ImporantCustmProject;
import com.misrobot.mismarketing.pojo.customerProject.ImportantCustomer;
import com.misrobot.mismarketing.pojo.customerStatistics.CustomerStatisticsInfo;
import com.misrobot.mismarketing.pojo.project.FirstFewProject;
import com.misrobot.mismarketing.pojo.projectStatistics.TopUserInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by GYQ on 2017/8/11.
 */
public class MyCompanyStatisticsInfo {
    private String mySales;
    private List<ImporantCustmProject> importantCustomerProjects;//我的客户项目
    //private Set<Map<Integer,ImporantCustmProject.ImportantCustomers>> importantCustomers;//重要客户
    private Map<Integer,ImporantCustmProject.ImportantCustomers> importantCustomers;//重要客户
    private TopSalesInfo topUserInfo;//第一名销售员
    private String avgSales;//平均销售额
    private List<CustomerStatisticsInfo> customerStatisticsInfos;//我的 客户每年的投资额
    private List<CustomerStatisticsInfo> projectStatisticsInfos;//我的项目每年的投资额
    private List<CustomerStatisticsInfo> projectStatisticsMonthInfos;//我的项目每月的投资额
    private String username;

    public String getMySales() {
        return mySales;
    }

    public void setMySales(String mySales) {
        this.mySales = mySales;
    }

    public List<ImporantCustmProject> getImportantCustomerProjects() {
        return importantCustomerProjects;
    }

    public void setImportantCustomerProjects(List<ImporantCustmProject> importantCustomerProjects) {
        this.importantCustomerProjects = importantCustomerProjects;
    }

    public TopSalesInfo getTopUserInfo() {
        return topUserInfo;
    }

    public void setTopUserInfo(TopSalesInfo topUserInfo) {
        this.topUserInfo = topUserInfo;
    }

    public String getAvgSales() {
        return avgSales;
    }

    public void setAvgSales(String avgSales) {
        this.avgSales = avgSales;
    }

    public List<CustomerStatisticsInfo> getCustomerStatisticsInfos() {
        return customerStatisticsInfos;
    }

    public void setCustomerStatisticsInfos(List<CustomerStatisticsInfo> customerStatisticsInfos) {
        this.customerStatisticsInfos = customerStatisticsInfos;
    }

    public List<CustomerStatisticsInfo> getProjectStatisticsInfos() {
        return projectStatisticsInfos;
    }

    public void setProjectStatisticsInfos(List<CustomerStatisticsInfo> projectStatisticsInfos) {
        this.projectStatisticsInfos = projectStatisticsInfos;
    }

    public List<CustomerStatisticsInfo> getProjectStatisticsMonthInfos() {
        return projectStatisticsMonthInfos;
    }

    public void setProjectStatisticsMonthInfos(List<CustomerStatisticsInfo> projectStatisticsMonthInfos) {
        this.projectStatisticsMonthInfos = projectStatisticsMonthInfos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Integer, ImporantCustmProject.ImportantCustomers> getImportantCustomers() {
        return importantCustomers;
    }

    public void setImportantCustomers(Map<Integer, ImporantCustmProject.ImportantCustomers> importantCustomers) {
        this.importantCustomers = importantCustomers;
    }
}
