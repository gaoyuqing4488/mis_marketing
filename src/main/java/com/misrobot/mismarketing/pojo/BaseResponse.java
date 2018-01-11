package com.misrobot.mismarketing.pojo;

/**
 * Created by GYQ on 2017/7/26.
 */
public class BaseResponse {
    //格式: /message/recschedulemes
    private String command;

    //错误码，成功为0，失败则为具体的非0错误码
    private String errcode;

    //错误描述
    private String errdesc;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrdesc() {
        return errdesc;
    }

    public void setErrdesc(String errdesc) {
        this.errdesc = errdesc;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

}
