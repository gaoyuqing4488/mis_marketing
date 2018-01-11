package com.misrobot.mismarketing.pojo.weekly;

import com.misrobot.mismarketing.entity.ProjectEntity;
import com.misrobot.mismarketing.pojo.BaseRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by GYQ on 2017/8/6.
 */
public class AddWeeklyReq  {

    private Long user_id;

    private String name;

    private BigDecimal sales;

    private List<WeeklyInfo> weekinfos;

    private List<ProjectEntity> projectEntitys;

    private Long starttime;

    private Long endtime;

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public List<ProjectEntity> getProjectEntitys() {
        return projectEntitys;
    }

    public void setProjectEntitys(List<ProjectEntity> projectEntitys) {
        this.projectEntitys = projectEntitys;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public List<WeeklyInfo> getWeekinfos() {
        return weekinfos;
    }

    public void setWeekinfos(List<WeeklyInfo> weekinfos) {
        this.weekinfos = weekinfos;
    }
}
