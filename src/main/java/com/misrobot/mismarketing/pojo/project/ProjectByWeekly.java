package com.misrobot.mismarketing.pojo.project;

/**
 * 我的项目名称编号
 * Created by GYQ on 2017/8/4.
 */
public class ProjectByWeekly {
    private Integer projectId;
    private String projectName;
    private  String projectNum;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
}
