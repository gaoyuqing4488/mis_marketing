package com.misrobot.mismarketing.pojo.weekly;

/**
 * 具体某个周报的详情信息
 * Created by GYQ on 2017/8/7.
 */
public class WeeklyResponse {

    private Integer id;

    private String name;

    private String content;

    private Integer project_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }
}
