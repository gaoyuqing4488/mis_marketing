package com.misrobot.mismarketing.pojo.weekly;

import java.util.List;

/**
 * Created by GYQ on 2017/8/3.
 */
public class AllWeeklyResponse {

    private List<AllWeeklys> list;

    private Integer counts;

    public List<AllWeeklys> getList() {
        return list;
    }

    public void setList(List<AllWeeklys> list) {
        this.list = list;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
