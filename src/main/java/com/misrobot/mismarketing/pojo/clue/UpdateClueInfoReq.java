package com.misrobot.mismarketing.pojo.clue;

import java.util.Date;

/**
 * Created by GYQ on 2018/1/4.
 */
public class UpdateClueInfoReq extends ClueList{


    private Date modifyDate;

    private Integer userId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }




}
