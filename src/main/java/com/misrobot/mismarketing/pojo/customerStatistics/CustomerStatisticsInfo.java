package com.misrobot.mismarketing.pojo.customerStatistics;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

/**
 * 客户每年的投资额
 * Created by GYQ on 2017/8/9.
 */
public class CustomerStatisticsInfo {

    private Integer years;

    @NumberFormat(pattern = "0.00")
    private BigDecimal money;

    private Integer months;

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    @NumberFormat(pattern = "0.00")
    public BigDecimal getMoney() {
        return money;
    }

    @NumberFormat(pattern = "0.00")
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
