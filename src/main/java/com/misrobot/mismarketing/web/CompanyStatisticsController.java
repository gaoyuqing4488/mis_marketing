package com.misrobot.mismarketing.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.projectStatistics.CompanyStatisticsInfo;
import com.misrobot.mismarketing.pojo.projectStatistics.MyCompanyStatisticsInfo;
import com.misrobot.mismarketing.pojo.projectStatistics.MyCustomerProjectInfo;
import com.misrobot.mismarketing.service.CompanyStatisticsService;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.JsonUtil;
import com.misrobot.mismarketing.base.RestCommand;
import com.misrobot.mismarketing.base.RestRequest;
import com.misrobot.mismarketing.base.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by GYQ on 2017/8/9.
 */
@Controller
public @RequestMapping("/statistics")
class CompanyStatisticsController {

    @Autowired
    private CompanyStatisticsService companyStatisticsService;

    /**
     * 获取公司报告统计
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getcompanystatistics")
    public @ResponseBody
    JsonNode getCompanyStatistics(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String loginid = command.getParameter("loginid");
                if (StringUtils.isEmpty(loginid)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                CompanyStatisticsInfo companyStatisticsInfo = companyStatisticsService.getCompanyStatisticsInfo(Long.valueOf(loginid), session);
                respCmd.setParameter(JsonUtil.getJSONString(companyStatisticsInfo));
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 获取我的销售报告
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getmystatistics")
    public @ResponseBody
    JsonNode getMyStatistics(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String loginid = command.getParameter("loginid");
                if (StringUtils.isEmpty(loginid)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                MyCompanyStatisticsInfo myCompanyStatisticsInfo = companyStatisticsService.getMyStatisticsInfo(Long.valueOf(loginid), session);
                respCmd.setParameter(JsonUtil.getJSONString(myCompanyStatisticsInfo));//myCompanyStatisticsInfo
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 获取我的或者公司客户项目统计
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getcustomerprojectstatistics")
    public @ResponseBody
    JsonNode getMyCustomerProjectStatistics(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String loginid = command.getParameter("loginid");
                String type = command.getParameter("type");
                if (StringUtils.isEmpty(loginid) || StringUtils.isEmpty(type)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                MyCustomerProjectInfo myCustomerProjectInfo = companyStatisticsService.getMyCustomerProjectInfo(Long.valueOf(loginid), Integer.valueOf(type));
                respCmd.setParameter(JsonUtil.getJSONString(myCustomerProjectInfo));
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }
}
