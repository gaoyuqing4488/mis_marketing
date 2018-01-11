package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.district.DistrictInfo;
import com.misrobot.mismarketing.pojo.district.DistrictResponse;

import java.util.List;

/**
 * Created by GYQ on 2017/12/7.
 */
public interface DistrictService {

    public List<DistrictResponse> getDistrictChildren() throws RestException;

}
