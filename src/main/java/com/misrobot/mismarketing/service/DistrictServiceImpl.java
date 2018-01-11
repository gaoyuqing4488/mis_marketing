package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.dao.district.DistrictDao;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.district.DistrictInfo;
import com.misrobot.mismarketing.pojo.district.DistrictResponse;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GYQ on 2017/12/7.
 */
@Service
public class DistrictServiceImpl extends AbstractService implements DistrictService {
    private static final Logger log = LoggerFactory.getLogger(DistrictServiceImpl.class);
    @Autowired
    private DistrictDao districtDao;

    @Override
    public List<DistrictResponse> getDistrictChildren() throws RestException {
        List<DistrictResponse> districtResponse = new ArrayList<DistrictResponse>();
        try {
            List<DistrictInfo> districtInfos = getDistrictsByPid(0);
            for (DistrictInfo districtInfo : districtInfos) {
                DistrictResponse response = new DistrictResponse();
                response.setId(districtInfo.getId());
                response.setProvince_id(0);
                response.setName(districtInfo.getName());
                List<DistrictInfo> districtInfoList = getDistrictsByPid(districtInfo.getId());
                if (districtInfoList.size() > 0) {
                    response.setDistrictChildren(districtInfoList);
                }
                districtResponse.add(response);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }

        return districtResponse;
    }

    private List<DistrictInfo> getDistrictsByPid(Integer pid) throws RestException {
        List<DistrictInfo> list = new ArrayList<DistrictInfo>();
        try {
            list = districtDao.getDistrictChildren(pid);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return list;
    }
}
