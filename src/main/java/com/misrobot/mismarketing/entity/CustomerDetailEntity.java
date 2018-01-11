package com.misrobot.mismarketing.entity;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.misrobot.mismarketing.pojo.BasePojo;

import java.io.UnsupportedEncodingException;

/**
 * Created by CHJ on 2017/8/2.
 */
public class CustomerDetailEntity extends BasePojo {

    private static final long serialVersionUID = 1L;

    private int id = 0;

    private int customerid = 0; //客户主表ID

    private int customerownerid = 0; //客户所有人ID

    private int infotype = 0; //信息类型 0-客户基础信息 1-客户机会点信息

    private int subinfotype = 0; //0 - 师生情况, 1 - 技能中心情况, 2 - 技能训练, 3 - 医院申报, 0 - 建设动因, 1 - 建设规模, 2 - 建设形式, 0 - 客户存在的主要痛点和问题,

    private String subinfocontent = ""; //信息内容（json 格式）

    public int getId() {
        return id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public int getInfotype() {
        return infotype;
    }

    public int getSubinfotype() {
        return subinfotype;
    }

    public String getSubinfocontent() {
        return subinfocontent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public void setInfotype(int infotype) {
        this.infotype = infotype;
    }

    public void setSubinfotype(int subinfotype) {
        this.subinfotype = subinfotype;
    }

    public void setSubinfocontent(String subinfocontent) {
        this.subinfocontent = subinfocontent;
    }

    public int getCustomerownerid() {
        return customerownerid;
    }

    public void setCustomerownerid(int customerownerid) {
        this.customerownerid = customerownerid;
    }
}
