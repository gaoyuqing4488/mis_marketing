package com.misrobot.mismarketing.pojo.projectStatistics;

import com.misrobot.mismarketing.pojo.customerProject.ImportantCustomer;
import com.misrobot.mismarketing.pojo.customerStatistics.CustomerStatisticsInfo;
import com.misrobot.mismarketing.pojo.project.FirstFewProject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by GYQ on 2017/8/9.
 */
public class CompanyStatisticsInfo {
    private Map<String,List<FirstFewProject>>  projectMap; //各大项目
    private Map<Integer,ImportantCustomer> customerMap;//去重重要客户
    private List<ImportantCustomer> importantCustomers;//重要客户
    private List<TopUserInfo> topUserInfos;//前10名销售员
    private String sales;//当年的销售总额
    private String avgSales;//平均销售额
    private Integer totals;//人数
    private List<CustomerStatisticsInfo> customerStatisticsInfos;//客户每年的投资额
    private List<CustomerStatisticsInfo> projectStatisticsInfos;//项目每年的投资额
    private List<CustomerStatisticsInfo> projectStatisticsMonthInfos;//每月的投资额
    private String username;

    public Map<Integer, ImportantCustomer> getCustomerMap() {
        return customerMap;
    }

    public void setCustomerMap(Map<Integer, ImportantCustomer> customerMap) {
        this.customerMap = customerMap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, List<FirstFewProject>> getProjectMap() {
        return projectMap;
    }

    public void setProjectMap(Map<String, List<FirstFewProject>> projectMap) {
        this.projectMap = projectMap;
    }

    public List<ImportantCustomer> getImportantCustomers() {
        return importantCustomers;
    }

    public void setImportantCustomers(List<ImportantCustomer> importantCustomers) {
        this.importantCustomers = importantCustomers;
    }

    public List<TopUserInfo> getTopUserInfos() {
        return topUserInfos;
    }

    public void setTopUserInfos(List<TopUserInfo> topUserInfos) {
        this.topUserInfos = topUserInfos;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getAvgSales() {
        return avgSales;
    }

    public void setAvgSales(String avgSales) {
        this.avgSales = avgSales;
    }

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
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
}
