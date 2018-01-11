package com.misrobot.mismarketing.pojo.customerProject;

/**
 * 客户项目关联信息表
 * Created by GYQ on 2017/8/8.
 */
public class CustomerProjectInfo {
    private Integer id;
    private Integer customer_id;
    private Integer project_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }
}
