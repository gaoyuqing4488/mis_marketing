package com.misrobot.mismarketing.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.misrobot.mismarketing.base.RestCommand;
import com.misrobot.mismarketing.base.RestRequest;
import com.misrobot.mismarketing.base.RestResponse;
import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.clue.*;
import com.misrobot.mismarketing.service.ClueService;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.JsonUtil;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GYQ on 2018/1/3.
 */
@Controller
public @RequestMapping("/clue")
class ClueController {

    @Autowired
    private ClueService clueService;

    /**
     * 新增线索
     *
     * @param parameters
     * @param session
     * @return
     */
    @RequestMapping("/addclue")
    public @ResponseBody
    JsonNode addClue(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String loginid = command.getParameter("loginid");
                String companyName = command.getParameter("companyname");
                String districtId = command.getParameter("districtid");
                String contactName = command.getParameter("contactname");
                String customerType = command.getParameter("customertype");
                String department = command.getParameter("department");
                String telephone = command.getParameter("telephone");
                String customerRate = command.getParameter("customerrate");
                String level = command.getParameter("level");
                String customerInitiative = command.getParameter("customerinitiative");
                String informationSources = command.getParameter("informationsources");
                String clueInfo = command.getParameter("clueinfo");
                String progress = command.getParameter("progress");
                String createrId = command.getParameter("createrid");
                String managerId = command.getParameter("managerid");
                String status = command.getParameter("status");
                String updateTime = command.getParameter("updatetime");
                if (StringUtils.isEmpty(loginid) || StringUtils.isEmpty(companyName) || StringUtils.isEmpty(districtId) ||
                        StringUtils.isEmpty(contactName) || StringUtils.isEmpty(customerType) || StringUtils.isEmpty(department)
                        || StringUtils.isEmpty(telephone) || StringUtils.isEmpty(customerRate) ||
                        StringUtils.isEmpty(level) || StringUtils.isEmpty(managerId)
                        || StringUtils.isEmpty(customerInitiative) || StringUtils.isEmpty(createrId) || StringUtils.isEmpty(status)
                        || StringUtils.isEmpty(informationSources) || StringUtils.isEmpty(clueInfo) || StringUtils.isEmpty(progress)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                Clue clue = new Clue();
                clue.setCompanyName(companyName);
                clue.setContactName(contactName);
                clue.setCreaterId(Integer.valueOf(createrId));
                clue.setCreateTime(new Date());
                clue.setCustomerInitiative(Integer.valueOf(customerInitiative));
                clue.setClueInfo(clueInfo);
                clue.setInformationSources(Integer.valueOf(informationSources));
                clue.setProgress(progress);
                clue.setCustomerRate(Integer.valueOf(customerRate));
                clue.setLevel(Integer.valueOf(level));
                clue.setCustomerType(Integer.valueOf(customerType));
                clue.setDepartment(department);
                clue.setDistrictId(Integer.valueOf(districtId));
                clue.setManagerId(Integer.valueOf(managerId));
                clue.setStatus(Integer.valueOf(status));
                clue.setTelephone(telephone);
                if (!StringUtils.isEmpty(updateTime)) {
                    clue.setUpdateTime(new Date());
                }
                Integer result = clueService.addClue(Integer.valueOf(loginid), clue, session);
                if (result != 0 && result != 2) {
                    respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
                } else if (result == 2) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.CLUE_EXIST));
                    respCmd.setStatusDescription(ErrorCode.CLUE_EXIST);
                } else {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response.toJsonObject();
    }

    /**
     * 获取线索列表
     *
     * @param parameters
     * @param session
     * @return
     */
    @RequestMapping("/getAllClues")
    public @ResponseBody
    JsonNode getAllClues(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                ClueListReq clueListReq = JsonUtil.getDTO(JsonUtil.getJSONString(command.toJsonObject()), ClueListReq.class);
                Integer requestPage = clueListReq.getRequestpage();
                Integer pageSize = clueListReq.getSizeperpage();
                if (requestPage != null && pageSize != null) {
                    int start = (requestPage - 1) * pageSize;
                    clueListReq.setRequestpage(start);
                }
                ClueListResponse clueListResponse = clueService.getAllClues(clueListReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(clueListResponse));
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 查看某个线索详情
     *
     * @param parameters
     * @param session
     * @return
     */
    @RequestMapping("/getClueInfo")
    public @ResponseBody
    JsonNode getClueInfo(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String loginid = command.getParameter("loginid");
                String id = command.getParameter("id");
                if (StringUtils.isEmpty(loginid) || StringUtils.isEmpty(id)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                ClueInfo clueInfo = clueService.getClueInfoById(Integer.valueOf(loginid), Integer.valueOf(id), session);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("clueInfo", clueInfo);
                respCmd.setParameter(JsonUtil.getJSONString(map));
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 修改线索
     *
     * @param parameters
     * @param session
     * @return
     */
    @RequestMapping("/updateclue")
    public @ResponseBody
    JsonNode updateClue(@RequestBody JsonNode parameters, HttpSession session) {
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
                UpdateClueInfoReq updateClueInfoReq = JsonUtil.getDTO(JsonUtil.getJSONString(command.toJsonObject()), UpdateClueInfoReq.class);
                clueService.updateClue(updateClueInfoReq, session);
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }
}

