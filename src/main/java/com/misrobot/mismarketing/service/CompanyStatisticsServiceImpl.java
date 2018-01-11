package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.dao.customer.CustomerEntityMapper;
import com.misrobot.mismarketing.dao.customer.CustomerStatisticsEntityMapper;
import com.misrobot.mismarketing.dao.mismarketing.MismarketingUserDao;
import com.misrobot.mismarketing.dao.project.ProjectEntityMapper;
import com.misrobot.mismarketing.dao.projectStatistics.ProjectStatisticsDao;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.customerProject.ImporantCustmProject;
import com.misrobot.mismarketing.pojo.customerProject.ImportantCustomer;
import com.misrobot.mismarketing.pojo.customerStatistics.CustomerStatisticsInfo;
import com.misrobot.mismarketing.pojo.mismarketing.dao.MismarketingUser;
import com.misrobot.mismarketing.pojo.project.FirstFewProject;
import com.misrobot.mismarketing.pojo.projectStatistics.*;
import com.misrobot.mismarketing.util.DecimalFormat;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by GYQ on 2017/8/9.
 */
@Service
public class CompanyStatisticsServiceImpl extends AbstractService implements CompanyStatisticsService {
    private static final Logger log = LoggerFactory.getLogger(CompanyStatisticsServiceImpl.class);

    @Autowired
    private ProjectStatisticsDao projectStatisticsDao;

    @Autowired
    private CustomerStatisticsEntityMapper customerStatisticsEntityMapper;

    @Autowired
    private MismarketingUserDao mismarketingAdminUserDao;

    @Autowired
    private MismarketingAdminUserService mismarketingAdminUserService;

    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    /**
     * 获取公司报告统计
     *
     * @param loginid
     * @return
     * @throws RestException
     */
    @Override
    public CompanyStatisticsInfo getCompanyStatisticsInfo(Long loginid, HttpSession session) throws RestException {
        CompanyStatisticsInfo companyStatisticsInfo = new CompanyStatisticsInfo();
        try {
            if (TokenUtil.checkLoginInfo(Long.valueOf(loginid), session)) {
                List<FirstFewProject> firstFewProjects = projectEntityMapper.getFirstProject();
                List<FirstFewProject> secondProject = projectEntityMapper.getSecondProject();
                List<FirstFewProject> importProject = projectEntityMapper.getImportProject();
                Map<String, List<FirstFewProject>> map = new HashMap<>();
                map.put("first", firstFewProjects);
                map.put("second", secondProject);
                map.put("important", importProject);
                BigDecimal allsales = projectStatisticsDao.getNewYearStatistics();//总的销售额
                String sales = DecimalFormat.getDecimalFormat(allsales);
                Integer total = mismarketingAdminUserDao.getSaleCounts();//总人数
                MismarketingUser mismarketingAdminUser = mismarketingAdminUserDao.getAdminUserById(loginid);
                BigDecimal totals = new BigDecimal(total);
                BigDecimal avgSale = allsales.divide(totals, 2, BigDecimal.ROUND_HALF_UP);//平均值
                String avgSales = DecimalFormat.getDecimalFormat(avgSale);
                List<TopUserInfo> topUserInfos = projectStatisticsDao.getTopUsers();//销售冠军
                List<ImportantCustomer> importantCustomers = customerEntityMapper.getImportCustomers();//重要客户
//                Map<Integer, ImportantCustomer> customerIdsMap = new HashMap<Integer, ImportantCustomer>();
//                for (ImportantCustomer importantCustomer : importantCustomers) {
//                    customerIdsMap.put(importantCustomer.getId(), importantCustomer);
//                }
                for(int i=0;i<importantCustomers.size();i++){
                   for(int j=importantCustomers.size()-1;j>i;j--){
                        if(importantCustomers.get(j).getId()==importantCustomers.get(i).getId()){
                                importantCustomers.remove(j);
                        }
                    }
                }
                List<CustomerStatisticsInfo> customerStatisticsInfos = customerStatisticsEntityMapper.getCustomerStatisticsByYear();//客户每年投资额
                List<CustomerStatisticsInfo> projectStatisticsByYears = projectStatisticsDao.getProjectStatisticsByYear();
                List<CustomerStatisticsInfo> projectStatisticsByMonths = projectStatisticsDao.getProjectStatisticsByMonth();
                companyStatisticsInfo.setAvgSales(avgSales);
                companyStatisticsInfo.setCustomerStatisticsInfos(customerStatisticsInfos);
                companyStatisticsInfo.setImportantCustomers(importantCustomers);
                companyStatisticsInfo.setProjectMap(map);
                companyStatisticsInfo.setProjectStatisticsInfos(projectStatisticsByYears);
                companyStatisticsInfo.setProjectStatisticsMonthInfos(projectStatisticsByMonths);
                companyStatisticsInfo.setSales(sales);
                companyStatisticsInfo.setTopUserInfos(topUserInfos);
                companyStatisticsInfo.setTotals(total);
                //companyStatisticsInfo.setCustomerMap(customerIdsMap);
                if (mismarketingAdminUser != null) {
                    companyStatisticsInfo.setUsername(mismarketingAdminUser.getUsername());
                }
            }
            return companyStatisticsInfo;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
    }

    /**
     * 获取我的销售报告
     *
     * @param loginid
     * @return
     * @throws RestException
     */
    @Override
    public MyCompanyStatisticsInfo getMyStatisticsInfo(Long loginid, HttpSession session) throws RestException {
        MyCompanyStatisticsInfo myCompanyStatisticsInfo = new MyCompanyStatisticsInfo();
        if (TokenUtil.checkLoginInfo(Long.valueOf(loginid), session)) {
            Map<String, Long> useridmap = new HashMap<>();
            try {
                useridmap.put("userid", loginid);
                List<ImporantCustmProject> imporantCustmProject = customerEntityMapper.getMyImportCustomers(useridmap);//我的项目与客户
                List<ImporantCustmProject.ImportantCustomers> mImportantCustomers = new ArrayList<>();
                Map<Integer, ImporantCustmProject.ImportantCustomers> mapList = new HashMap<>();
//                for (ImporantCustmProject iCustmProject : imporantCustmProject) {
//                    ImporantCustmProject.ImportantCustomers importantCustomers = ImporantCustmProject.ImportantCustomers.assign(iCustmProject);
//                    // Map<Integer,ImporantCustmProject.ImportantCustomers> importantCustomersMap=new HashMap<>();
//
//                    if (!mapList.containsKey(iCustmProject.getCustomerid())) {
//                        mapList.put(iCustmProject.getCustomerid(), importantCustomers);
//                    }
//
//                    // mImportantCustomers.add(importantCustomers);
//                }
                for(int i=0;i<imporantCustmProject.size();i++){
                    for(int j=imporantCustmProject.size()-1;j>i;j--){
                        if(imporantCustmProject.get(j).getCustomerid()==imporantCustmProject.get(i).getCustomerid()){
                            imporantCustmProject.remove(j);
                        }
                    }
                }
                //我的客户
                // Set<Map<Integer,ImporantCustmProject.ImportantCustomers>> importantCustomersSet=new HashSet<>(mapList);
                BigDecimal myNewYearMoney = projectStatisticsDao.getMyNewYearStatistics(useridmap);
                String sales = DecimalFormat.getDecimalFormat(myNewYearMoney);
//                Integer total = mismarketingAdminUserDao.getSaleCounts();//总人数
                MismarketingUser mismarketingAdminUser = mismarketingAdminUserDao.getAdminUserById(loginid);
//                BigDecimal totals = new BigDecimal(total);
//                BigDecimal avgSale = myNewYearMoney.divide(totals, 2, BigDecimal.ROUND_HALF_UP);
//                String avgSales = DecimalFormat.getDecimalFormat(avgSale);//平均值
                BigDecimal allsales = projectStatisticsDao.getNewYearStatistics();//总的销售额
                // String sales = DecimalFormat.getDecimalFormat(allsales);
                Integer total = mismarketingAdminUserDao.getSaleCounts();//总人数
                BigDecimal totals = new BigDecimal(total);
                BigDecimal avgSale = allsales.divide(totals, 2, BigDecimal.ROUND_HALF_UP);//平均值
                String avgSales = DecimalFormat.getDecimalFormat(avgSale);
                TopSalesInfo topSalesInfo = projectStatisticsDao.getTopProjectStatistics();
                List<CustomerStatisticsInfo> myCustomerStatisticsInfos = customerStatisticsEntityMapper.getMyCustomerStatisticsByYear(useridmap);//我的客户投资额
                List<CustomerStatisticsInfo> myProjectStatisticsByYear = projectStatisticsDao.getMyProjectStatisticsByYear(useridmap);
                List<CustomerStatisticsInfo> myProjectStatisticsByMonth = projectStatisticsDao.getMyProjectStatisticsByMonth(useridmap);
                myCompanyStatisticsInfo.setAvgSales(avgSales);
                myCompanyStatisticsInfo.setCustomerStatisticsInfos(myCustomerStatisticsInfos);
                myCompanyStatisticsInfo.setImportantCustomerProjects(imporantCustmProject);
                myCompanyStatisticsInfo.setMySales(sales);
                myCompanyStatisticsInfo.setProjectStatisticsInfos(myProjectStatisticsByYear);
                myCompanyStatisticsInfo.setProjectStatisticsMonthInfos(myProjectStatisticsByMonth);
                myCompanyStatisticsInfo.setTopUserInfo(topSalesInfo);
               // myCompanyStatisticsInfo.setImportantCustomers(mapList);
                if (mismarketingAdminUser != null) {
                    myCompanyStatisticsInfo.setUsername(mismarketingAdminUser.getUsername());
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                        ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
            }

        }
        return myCompanyStatisticsInfo;
    }

    private final int MY_CUSTOMER_STATISTICS_TYPE = 1;

    private final int ALL_CUSTOMER_STATISTICS_TYPE = 0;

    @Override
    public MyCustomerProjectInfo getMyCustomerProjectInfo(Long loginid, Integer type) throws RestException {
        MyCustomerProjectInfo myCustomerProjectInfo = new MyCustomerProjectInfo();
        Map<String, Long> useridmap = new HashMap<>();
        try {
            useridmap.put("userid", loginid);
            MyCustomerProjectStatistics myCustomerProjectStatistics = null;
            //我的客户项目统计
            if (MY_CUSTOMER_STATISTICS_TYPE == type) {
                myCustomerProjectStatistics = projectStatisticsDao.getMyProjectMoney(useridmap);
            }
            //公司的客户项目统计
            if (ALL_CUSTOMER_STATISTICS_TYPE == type) {
                myCustomerProjectStatistics = projectStatisticsDao.getAllProjectMoney();
            }
            //客户总的预计额
            BigDecimal money_current = myCustomerProjectStatistics.getCurrent_budget_money();
            BigDecimal money_1 = myCustomerProjectStatistics.getBudget_money_1();
            BigDecimal money_2 = myCustomerProjectStatistics.getBudget_money_2();
            BigDecimal total = new BigDecimal(money_current.floatValue() + money_1.floatValue() + money_2.floatValue());
            myCustomerProjectStatistics.setTotal_budget_money(total);
            String total_budget_money = DecimalFormat.getDecimalFormat(myCustomerProjectStatistics.getTotal_budget_money());
            //客户当年预计额
            String current_budget_money = DecimalFormat.getDecimalFormat(myCustomerProjectStatistics.getCurrent_budget_money());
            //客户第二年预计销售额
            String budget_money_1 = DecimalFormat.getDecimalFormat(myCustomerProjectStatistics.getBudget_money_1());
            //客户第三年预计销售额
            String budget_money_2 = DecimalFormat.getDecimalFormat(myCustomerProjectStatistics.getBudget_money_2());
            //客户今年实际销售额
            String current_actual_money = DecimalFormat.getDecimalFormat(myCustomerProjectStatistics.getCurrent_actual_money());
            myCustomerProjectInfo.setTotal_budget_money(total_budget_money);
            myCustomerProjectInfo.setCurrent_budget_money(current_budget_money);
            myCustomerProjectInfo.setBudget_money_1(budget_money_1);
            myCustomerProjectInfo.setBudget_money_2(budget_money_2);
            myCustomerProjectInfo.setCurrent_actual_money(current_actual_money);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }

        return myCustomerProjectInfo;
    }
}
