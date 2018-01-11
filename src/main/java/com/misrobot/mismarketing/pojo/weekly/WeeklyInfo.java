package com.misrobot.mismarketing.pojo.weekly;

import com.google.gson.JsonObject;
import com.misrobot.mismarketing.pojo.BasePojo;

import java.math.BigDecimal;

/**
 * 项目周报信息
 * Created by GYQ on 2017/8/2.
 */
public class WeeklyInfo extends BasePojo {

    private Integer id;

    private Integer project_id;

    private Integer weekly_id;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getWeekly_id() {
        return weekly_id;
    }

    public void setWeekly_id(Integer weekly_id) {
        this.weekly_id = weekly_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
