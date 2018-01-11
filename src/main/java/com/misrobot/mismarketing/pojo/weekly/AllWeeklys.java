package com.misrobot.mismarketing.pojo.weekly;

import java.math.BigDecimal;

/**
 * 获取周报列表封装的类
 * Created by GYQ on 2017/8/3.
 */
public class AllWeeklys extends Weekly {

    private String username;

    private String districtName;

    //private BigDecimal sales;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

//    public BigDecimal getSales() {
//        return sales;
//    }
//
//    public void setSales(BigDecimal sales) {
//        this.sales = sales;
//    }
}
