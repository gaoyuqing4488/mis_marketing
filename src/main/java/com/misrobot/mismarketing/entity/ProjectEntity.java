package com.misrobot.mismarketing.entity;

import com.misrobot.mismarketing.pojo.BasePojo;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * Created by CHJ on 2017/8/2.
 */
public class ProjectEntity extends BasePojo {

    private static final long serialVersionUID = 1L;

    private int id = 0;

    private String projectname = "";

    private int rate = 0; //项目级别

    private int step = 0; //当前项目阶段

    private String infoprogress = ""; //基本信息和进展

    private String difficultyhelp = ""; //困难与求助

    private BigDecimal currentbudgetmoney = new BigDecimal(1); //当年预算销售额

    private BigDecimal currentactualmoney = new BigDecimal(1); //当年实际销售额

    private Timestamp projectdate = Timestamp.from(Instant.EPOCH); //立项日期

    private Timestamp capitaldate = Timestamp.from(Instant.EPOCH); //资金到位日期

    private Timestamp capitalusingdate = Timestamp.from(Instant.EPOCH); //资金使用日期

    private Timestamp bidingdocumentdate = Timestamp.from(Instant.EPOCH); //发标日期

    private Timestamp billsignplandate = Timestamp.from(Instant.EPOCH); //预计签单日期

    private Timestamp billsignactualdate = Timestamp.from(Instant.EPOCH); //实际签单日期

    private Timestamp commercialplandate = Timestamp.from(Instant.EPOCH); //预计商用日期

    private Timestamp commercialactualdate = Timestamp.from(Instant.EPOCH); //实际商用日期

    private String majordomo = ""; //项目总监

    private String deputymajordomo = ""; //项目副总监

    private int projectcustomerid = 0; //项目客户ID

    private String projectcustomer = ""; //项目客户

    private String customerabbreviation = ""; //客户简称

    private String projectnumber = ""; //项目编号

    private String projectabbreviation = ""; //项目简称

    private int projectownerid = 0; //销售经理 ID

    private String projectowner = ""; //项目所有人(销售人员)

    private BigDecimal totalbudgetmoney = new BigDecimal(1); //预计销售额

    private BigDecimal budgetmoney_1 = new BigDecimal(1); //下一年预算额度

    private BigDecimal budgetmoney_2 = new BigDecimal(1); //下下年预算额度
    private Timestamp createtime = Timestamp.from(Instant.now()); //创建时间

    public int getId() {
        return id;
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

    public int getProjectownerid() {
        return projectownerid;
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

    public void setId(int id) {
        this.id = id;
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

    public void setProjectownerid(int projectownerid) {
        this.projectownerid = projectownerid;
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

    public int getProjectcustomerid() {
        return projectcustomerid;
    }

    public void setProjectcustomerid(int projectcustomerid) {
        this.projectcustomerid = projectcustomerid;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}
