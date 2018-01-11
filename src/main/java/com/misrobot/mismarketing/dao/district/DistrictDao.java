package com.misrobot.mismarketing.dao.district;

import com.misrobot.mismarketing.pojo.district.DistrictInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by GYQ on 2017/9/20.
 */
public interface DistrictDao {

    public List<DistrictInfo> getAllDistricts(Map<String,String> map)throws SQLException;

    public List<DistrictInfo> getDistrictChildren(Integer province_id)throws SQLException;
}
