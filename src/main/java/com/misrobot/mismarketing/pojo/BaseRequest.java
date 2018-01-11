package com.misrobot.mismarketing.pojo;

/**
 * Created by GYQ on 2017/7/26.
 */
public class BaseRequest {
    //请求的URL 格式: /message/recschedulemes
    private String command;

    private String sessionid;

    private String loginid;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
}
