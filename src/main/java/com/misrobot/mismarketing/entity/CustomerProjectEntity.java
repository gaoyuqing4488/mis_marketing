package com.misrobot.mismarketing.entity;

import com.misrobot.mismarketing.pojo.BasePojo;

/**
 * Created by CHJ on 2017/9/28.
 */
public class CustomerProjectEntity extends BasePojo {

    private static final long serialVersionUID = 1L;

    private int id;

    private int customerid;

    private int projectid;

    public int getId() {
        return id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }
}
