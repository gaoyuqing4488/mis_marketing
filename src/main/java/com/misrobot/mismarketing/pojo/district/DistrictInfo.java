package com.misrobot.mismarketing.pojo.district;

/**
 * Created by GYQ on 2017/9/20.
 */
public class DistrictInfo {
    private Integer id;

    private String name;

    private Integer province_id;

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
