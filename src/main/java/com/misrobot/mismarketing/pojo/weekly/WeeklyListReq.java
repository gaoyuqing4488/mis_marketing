package com.misrobot.mismarketing.pojo.weekly;

import com.misrobot.mismarketing.pojo.BaseRequest;

import java.util.Date;

/**
 * 查询所有周报的过滤信息
 * Created by GYQ on 2017/8/3.
 */
public class WeeklyListReq extends BaseRequest {

    private String username;

    private Date begintime;

    private Date endtime;

    private String districtname;

    private Long userid;

    private Integer requestpage;

    private Integer sizeperpage;

    private Integer orderby;

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public Integer getRequestpage() {
        return requestpage;
    }

    public void setRequestpage(Integer requestpage) {
        this.requestpage = requestpage;
    }

    public Integer getSizeperpage() {
        return sizeperpage;
    }

    public void setSizeperpage(Integer sizeperpage) {
        this.sizeperpage = sizeperpage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
