package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.entity.ProjectEntity;
import com.misrobot.mismarketing.pojo.BaseResponse;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CHJ on 2017/8/6.
 */
public class ProjectInfoResponse extends BaseResponse {

    public ArrayList<ProjectInfoUnit> queryprojectlist = new ArrayList<>();

    public ArrayList<ProjectInfoUnit> getQueryprojectlist() {
        return queryprojectlist;
    }

    public void setQueryprojectlist(ArrayList<ProjectInfoUnit> queryprojectlist) {
        this.queryprojectlist = queryprojectlist;
    }

    public static class ProjectInfoUnit {
        private int projectid = 0;
        private String projectname = "";
        private int rate = 0; //项目级别
        private int step = 0; //当前项目阶段
        private String infoprogress = ""; //基本信息和进展
        private String difficultyhelp = ""; //困难与求助
        private BigDecimal currentbudgetmoney = new BigDecimal(1); //当年预算销售额
        private BigDecimal currentactualmoney = new BigDecimal(1); //当年实际销售额
        private Timestamp projectdate = null; //立项日期
        private Timestamp capitaldate = null; //资金到位日期
        private Timestamp capitalusingdate = null; //资金使用日期
        private Timestamp bidingdocumentdate = null; //发标日期
        private Timestamp billsignplandate = null; //预计签单日期
        private Timestamp billsignactualdate = null; //实际签单日期
        private Timestamp commercialplandate = null; //预计商用日期
        private Timestamp commercialactualdate = null; //实际商用日期
        private String majordomo = ""; //项目总监
        private String deputymajordomo = ""; //项目副总监
        private int salesmanagerid = 0; //销售经理 ID
        private int projectcustomerid = 0; //项目客户ID
        private String projectcustomer = ""; //项目客户
        private String customerabbreviation = ""; //客户简称
        private String projectnumber = ""; //项目编号
        private String projectabbreviation = ""; //项目简称
        private String projectowner = ""; //项目所有人
        private BigDecimal totalbudgetmoney = new BigDecimal(1); //预计销售额
        private BigDecimal budgetmoney_1 = new BigDecimal(1); //下一年预算额度
        private BigDecimal budgetmoney_2 = new BigDecimal(1); //下下年预算额度
        private Timestamp createtime = Timestamp.from(Instant.EPOCH); //创建时间

        public int getProjectid() {
            return projectid;
        }

        public String getProjectname() {
            return projectname;
        }

        public int getRate() {
            return rate;
        }

        public int getStep() {
            return step;
        }

        public String getInfoprogress() {
            return infoprogress;
        }

        public String getDifficultyhelp() {
            return difficultyhelp;
        }

        public BigDecimal getCurrentbudgetmoney() {
            return currentbudgetmoney;
        }

        public BigDecimal getCurrentactualmoney() {
            return currentactualmoney;
        }

        public Timestamp getProjectdate() {
            return projectdate;
        }

        public Timestamp getCapitaldate() {
            return capitaldate;
        }

        public Timestamp getCapitalusingdate() {
            return capitalusingdate;
        }

        public Timestamp getBidingdocumentdate() {
            return bidingdocumentdate;
        }

        public Timestamp getBillsignplandate() {
            return billsignplandate;
        }

        public Timestamp getBillsignactualdate() {
            return billsignactualdate;
        }

        public Timestamp getCommercialplandate() {
            return commercialplandate;
        }

        public Timestamp getCommercialactualdate() {
            return commercialactualdate;
        }

        public String getMajordomo() {
            return majordomo;
        }

        public String getDeputymajordomo() {
            return deputymajordomo;
        }

        public int getSalesmanagerid() {
            return salesmanagerid;
        }

        public int getProjectcustomerid() {
            return projectcustomerid;
        }

        public String getProjectcustomer() {
            return projectcustomer;
        }

        public String getCustomerabbreviation() {
            return customerabbreviation;
        }

        public String getProjectnumber() {
            return projectnumber;
        }

        public String getProjectabbreviation() {
            return projectabbreviation;
        }

        public String getProjectowner() {
            return projectowner;
        }

        public BigDecimal getTotalbudgetmoney() {
            return totalbudgetmoney;
        }

        public BigDecimal getBudgetmoney_1() {
            return budgetmoney_1;
        }

        public BigDecimal getBudgetmoney_2() {
            return budgetmoney_2;
        }

        public void setProjectid(int projectid) {
            this.projectid = projectid;
        }

        public void setProjectname(String projectname) {
            this.projectname = projectname;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public void setInfoprogress(String infoprogress) {
            this.infoprogress = infoprogress;
        }

        public void setDifficultyhelp(String difficultyhelp) {
            this.difficultyhelp = difficultyhelp;
        }

        public void setCurrentbudgetmoney(BigDecimal currentbudgetmoney) {
            this.currentbudgetmoney = currentbudgetmoney;
        }

        public void setCurrentactualmoney(BigDecimal currentactualmoney) {
            this.currentactualmoney = currentactualmoney;
        }

        public void setProjectdate(Timestamp projectdate) {
            this.projectdate = projectdate;
        }

        public void setCapitaldate(Timestamp capitaldate) {
            this.capitaldate = capitaldate;
        }

        public void setCapitalusingdate(Timestamp capitalusingdate) {
            this.capitalusingdate = capitalusingdate;
        }

        public void setBidingdocumentdate(Timestamp bidingdocumentdate) {
            this.bidingdocumentdate = bidingdocumentdate;
        }

        public void setBillsignplandate(Timestamp billsignplandate) {
            this.billsignplandate = billsignplandate;
        }

        public void setBillsignactualdate(Timestamp billsignactualdate) {
            this.billsignactualdate = billsignactualdate;
        }

        public void setCommercialplandate(Timestamp commercialplandate) {
            this.commercialplandate = commercialplandate;
        }

        public void setCommercialactualdate(Timestamp commercialactualdate) {
            this.commercialactualdate = commercialactualdate;
        }

        public void setMajordomo(String majordomo) {
            this.majordomo = majordomo;
        }

        public void setDeputymajordomo(String deputymajordomo) {
            this.deputymajordomo = deputymajordomo;
        }

        public void setSalesmanagerid(int salesmanagerid) {
            this.salesmanagerid = salesmanagerid;
        }

        public void setProjectcustomerid(int projectcustomerid) {
            this.projectcustomerid = projectcustomerid;
        }

        public void setProjectcustomer(String projectcustomer) {
            this.projectcustomer = projectcustomer;
        }

        public void setCustomerabbreviation(String customerabbreviation) {
            this.customerabbreviation = customerabbreviation;
        }

        public void setProjectnumber(String projectnumber) {
            this.projectnumber = projectnumber;
        }

        public void setProjectabbreviation(String projectabbreviation) {
            this.projectabbreviation = projectabbreviation;
        }

        public void setProjectowner(String projectowner) {
            this.projectowner = projectowner;
        }

        public void setTotalbudgetmoney(BigDecimal totalbudgetmoney) {
            this.totalbudgetmoney = totalbudgetmoney;
        }

        public void setBudgetmoney_1(BigDecimal budgetmoney_1) {
            this.budgetmoney_1 = budgetmoney_1;
        }

        public void setBudgetmoney_2(BigDecimal budgetmoney_2) {
            this.budgetmoney_2 = budgetmoney_2;
        }

        public Timestamp getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Timestamp createtime) {
            this.createtime = createtime;
        }

        public ProjectInfoUnit init(ProjectEntity entity) {
            this.projectid = entity.getId();
            this.projectname = entity.getProjectname();
            this.rate = entity.getRate();
            this.step = entity.getStep();
            this.infoprogress = entity.getInfoprogress();
            this.difficultyhelp = entity.getDifficultyhelp();
            this.currentbudgetmoney = entity.getCurrentbudgetmoney();
            this.currentactualmoney = entity.getCurrentactualmoney();
            if (!Timestamp.from(Instant.EPOCH).equals(entity.getProjectdate()))
                this.projectdate = entity.getProjectdate();
            if (!Timestamp.from(Instant.EPOCH).equals(entity.getCapitaldate()))
                this.capitaldate = entity.getCapitaldate();
            if (!Timestamp.from(Instant.EPOCH).equals(entity.getCapitalusingdate()))
                this.capitalusingdate = entity.getCapitalusingdate();
            if (!Timestamp.from(Instant.EPOCH).equals(entity.getBidingdocumentdate()))
                this.bidingdocumentdate = entity.getBidingdocumentdate();
            if (!Timestamp.from(Instant.EPOCH).equals(entity.getBillsignplandate()))
                this.billsignplandate = entity.getBillsignplandate();
            if (!Timestamp.from(Instant.EPOCH).equals(entity.getBillsignactualdate()))
                this.billsignactualdate = entity.getBillsignactualdate();
            if (!Timestamp.from(Instant.EPOCH).equals(entity.getCommercialplandate()))
                this.commercialplandate = entity.getCommercialplandate();
            if (!Timestamp.from(Instant.EPOCH).equals(entity.getCommercialactualdate()))
                this.commercialactualdate = entity.getCommercialactualdate();
            this.majordomo = entity.getMajordomo();
            this.deputymajordomo = entity.getDeputymajordomo();
            this.salesmanagerid = entity.getProjectownerid();
            this.projectcustomerid = entity.getProjectcustomerid();
            this.projectcustomer = entity.getProjectcustomer();
            this.customerabbreviation = entity.getCustomerabbreviation();
            this.projectnumber = entity.getProjectnumber();
            this.projectabbreviation = entity.getProjectabbreviation();
            this.projectowner = entity.getProjectowner();
            this.totalbudgetmoney = entity.getTotalbudgetmoney();
            this.budgetmoney_1 = entity.getBudgetmoney_1();
            this.budgetmoney_2 = entity.getBudgetmoney_2();
            this.createtime = entity.getCreatetime();

            return this;
        }
    }
}
