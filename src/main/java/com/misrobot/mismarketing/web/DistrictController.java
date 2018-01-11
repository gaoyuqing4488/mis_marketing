package com.misrobot.mismarketing.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.misrobot.mismarketing.base.RestCommand;
import com.misrobot.mismarketing.base.RestRequest;
import com.misrobot.mismarketing.base.RestResponse;
import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.district.DistrictResponse;
import com.misrobot.mismarketing.service.DistrictService;
import com.misrobot.mismarketing.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GYQ on 2017/12/7.
 */
@Controller
public @RequestMapping("/district")
class DistrictController {
    @Autowired
    private DistrictService districtService;

    /**
     * 获取所有的地区
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getalldistricts")
    public @ResponseBody
    JsonNode getAllDistricts(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        List<DistrictResponse> districtResponses = new ArrayList<DistrictResponse>();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                districtResponses = districtService.getDistrictChildren();
                Map<String, Object> map = new HashMap<>();
                map.put("districtResponses", districtResponses);
                respCmd.setParameter(JsonUtil.getJSONString(map));
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }
}
