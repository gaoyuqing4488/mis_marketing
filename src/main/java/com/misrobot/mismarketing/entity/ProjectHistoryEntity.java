package com.misrobot.mismarketing.entity;

import com.misrobot.mismarketing.pojo.BasePojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by CHJ on 2017/8/2.
 */
public class ProjectHistoryEntity extends BasePojo {
    private static final long serialVersionUID = 1L;

    private int id = 0;

    private int projectid = 0;

    private Timestamp modifydate; //修改日期

    private String title = "无标题"; //修改记录标题

    private String modifycontent = "无内容"; //修改内容

    public int getId() {
        return id;
    }

    public Timestamp getModifydate() {
        return modifydate;
    }

    public String getTitle() {
        return title;
    }

    public String getModifycontent() {
        return modifycontent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModifydate(Timestamp modifydate) {
        this.modifydate = modifydate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setModifycontent(String modifycontent) {
        this.modifycontent = modifycontent;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }
}
