package com.misrobot.mismarketing.pojo.projectStatistics;

import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

/**
 * 当年最高销售额信息
 * Created by GYQ on 2017/8/11.
 */
public class TopSalesInfo {
    @NumberFormat(pattern = "0.00")
    private BigDecimal sales = new BigDecimal(0.00);

    private Long userid;

    @NumberFormat(pattern = "0.00")
    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
