package com.misrobot.mismarketing.pojo.district;

import java.util.List;

/**
 * Created by GYQ on 2017/12/7.
 */
public class DistrictResponse {
    private Integer id;

    private Integer province_id;

    private String name;

    private List<DistrictInfo> districtChildren;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictInfo> getDistrictChildren() {
        return districtChildren;
    }

    public void setDistrictChildren(List<DistrictInfo> districtChildren) {
        this.districtChildren = districtChildren;
    }
}
