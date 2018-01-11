package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.pojo.BaseRequest;

/**
 * 功能描述 记录rest日志
 *
 * @author 作者 Created by GYQ on 2017/7/26
 * @version 1.0.0
 */

public class LogPlatformReq extends BaseRequest {
    private String direction;

    private String logcontent;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLogcontent() {
        return logcontent;
    }

    public void setLogcontent(String logcontent) {
        this.logcontent = logcontent;
    }
}
