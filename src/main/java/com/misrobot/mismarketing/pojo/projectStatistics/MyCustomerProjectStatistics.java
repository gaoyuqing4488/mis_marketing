package com.misrobot.mismarketing.pojo.projectStatistics;

import java.math.BigDecimal;

/**
 * 我 的所有客户项目的统计
 * Created by GYQ on 2017/10/10.
 */
public class MyCustomerProjectStatistics {
    private BigDecimal total_budget_money;//客户总的预算额
    private BigDecimal current_budget_money;//客户当年预算额
    private BigDecimal budget_money_1;//第二年预算额
    private BigDecimal budget_money_2;//第三年预算额
    private BigDecimal current_actual_money;//当年实际销售额

    public BigDecimal getTotal_budget_money() {
        return total_budget_money;
    }

    public void setTotal_budget_money(BigDecimal total_budget_money) {
        this.total_budget_money = total_budget_money;
    }

    public BigDecimal getCurrent_budget_money() {
        return current_budget_money;
    }

    public void setCurrent_budget_money(BigDecimal current_budget_money) {
        this.current_budget_money = current_budget_money;
    }

    public BigDecimal getBudget_money_1() {
        return budget_money_1;
    }

    public void setBudget_money_1(BigDecimal budget_money_1) {
        this.budget_money_1 = budget_money_1;
    }

    public BigDecimal getBudget_money_2() {
        return budget_money_2;
    }

    public void setBudget_money_2(BigDecimal budget_money_2) {
        this.budget_money_2 = budget_money_2;
    }

    public BigDecimal getCurrent_actual_money() {
        return current_actual_money;
    }

    public void setCurrent_actual_money(BigDecimal current_actual_money) {
        this.current_actual_money = current_actual_money;
    }


}
