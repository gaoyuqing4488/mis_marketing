package com.misrobot.mismarketing.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.entity.ProjectEntity;
import com.misrobot.mismarketing.entity.ProjectStatisticsEntity;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.weekly.*;
import com.misrobot.mismarketing.service.WeeklyService;
import com.misrobot.mismarketing.util.DateUtil;
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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by GYQ on 2017/8/3.
 */
@Controller
public @RequestMapping("/weekly")
class WeeklyController {

    @Autowired
    private WeeklyService weeklyService;

    /**
     * 获取我的周报列表
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getmyallweeklys")
    public @ResponseBody
    JsonNode getMyWeeklys(@RequestBody JsonNode parameters, HttpSession session) {

        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                WeeklyListReq weeklyListReq = JsonUtil.getDTO(JsonUtil.getJSONString(command.toJsonObject()), WeeklyListReq.class);
                Integer requestPage = weeklyListReq.getRequestpage();
                Integer pageSize = weeklyListReq.getSizeperpage();
                String name = weeklyListReq.getDistrictname();
                String username = weeklyListReq.getUsername();
                if (requestPage != null && pageSize != null) {
                    int start = (requestPage - 1) * pageSize;
                    weeklyListReq.setRequestpage(start);
                }
                if ("".equals(name)) {
                    weeklyListReq.setDistrictname(null);
                }
                if (StringUtils.isEmpty(username)) {
                    weeklyListReq.setUsername(null);
                }
                AllWeeklyResponse allWeeklyResponse = weeklyService.getMyWeeklys(weeklyListReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(allWeeklyResponse));
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 管理员获取所有周报列表
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getallweeklys")
    public @ResponseBody
    JsonNode getAllWeeklys(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                WeeklyListReq weeklyListReq = JsonUtil.getDTO(JsonUtil.getJSONString(command.toJsonObject()), WeeklyListReq.class);
                Integer requestPage = weeklyListReq.getRequestpage();
                Integer pageSize = weeklyListReq.getSizeperpage();
                if (requestPage != null && pageSize != null) {
                    int start = (requestPage - 1) * pageSize;
                    weeklyListReq.setRequestpage(start);
                }
                // Date createTime=weeklyListReq.getBegintime();
                if (weeklyListReq.getEndtime() != null) {
                    Date endTime = weeklyListReq.getEndtime();
                    Long endTimeStamp = endTime.getTime() + (23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
                    endTime.setTime(endTimeStamp);
                    weeklyListReq.setEndtime(endTime);
                }
                AllWeeklyResponse allWeeklyResponse = weeklyService.getAllWeeklys(weeklyListReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(allWeeklyResponse));
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 新增周报前的基本信息
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getweeklyname")
    public @ResponseBody
    JsonNode getWeeklyName(@RequestBody JsonNode parameters) {
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
                Map<String, Long> map = new HashMap<>();
                map.put("userid", Long.valueOf(loginid));
                WeeklyNameResponse weeklyNameResponse = weeklyService.getWeeklyName(Integer.valueOf(type), map, Long.valueOf(loginid));
                respCmd.setParameter(JsonUtil.getJSONString(weeklyNameResponse));
//                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 添加周报
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/addweekly")
    public @ResponseBody
    JsonNode addWeekly(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String loginid = command.getParameter("loginid");
                String name = command.getParameter("name");
                String sales = command.getParameter("sales");
                String starttime = command.getParameter("starttime");
                String endtime = command.getParameter("endtime");
                JsonNode jsonNode = command.getParameterAsJsonNode("weekinfos");
                List<WeeklyInfo> list = new ArrayList<>();
                if (StringUtils.isEmpty(loginid) || StringUtils.isEmpty(name) || StringUtils.isEmpty(sales) ||
                        StringUtils.isEmpty(starttime) || StringUtils.isEmpty(endtime)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                List<Integer> prjectids = new ArrayList<>();
                List<ProjectEntity> projectEntities = new ArrayList<ProjectEntity>();
                if (jsonNode instanceof ArrayNode) {
                    ArrayNode an = (ArrayNode) jsonNode;
                    for (int i = 0; i < an.size(); i++) {
                        JsonNode jn = an.get(i);
                        JsonNode t = jn.get("project_id");
                        JsonNode c = jn.get("content");
                        //项目跟新
                        JsonNode projectName = jn.get("projectname");
                        JsonNode weekly_id = jn.get("weekly_id");
                        JsonNode rates = jn.get("rate");
                        JsonNode steps = jn.get("step");
                        JsonNode infoProgress = jn.get("infoprogress");
                        JsonNode difficultyHelp = jn.get("difficultyhelp");
                        JsonNode currentbudgetMoney = jn.get("currentbudgetmoney");
                        JsonNode currentactualMoney = jn.get("currentactualmoney");
                        JsonNode projectdate = jn.get("projectdate");
                        JsonNode capitaldate = jn.get("capitaldate");
                        JsonNode capitalusingdate = jn.get("capitalusingdate");
                        JsonNode bidingdocumentdate = jn.get("bidingdocumentdate");
                        JsonNode billsignplandate = jn.get("billsignplandate");
                        JsonNode billsignactualdate = jn.get("billsignactualdate");
                        JsonNode commercialplandate = jn.get("commercialplandate");
                        JsonNode commercialactualdate = jn.get("commercialactualdate");
                        JsonNode majordomo = jn.get("majordomo");
                        JsonNode deputymajordomo = jn.get("deputymajordomo");
                        JsonNode projectcustomerid = jn.get("projectcustomerid");
                        JsonNode projectcustomer = jn.get("projectcustomer");
                        JsonNode customerabbreviation = jn.get("customerabbreviation");
                        JsonNode projectNumber = jn.get("projectnumber");
                        JsonNode projectabbreViation = jn.get("projectabbreviation");
                        JsonNode projectownerId = jn.get("projectownerid");
                        JsonNode projectOwner = jn.get("projectowner");
                        JsonNode totalbudgetMoney = jn.get("totalbudgetmoney");
                        JsonNode budgetMoney_1 = jn.get("budgetmoney_1");
                        JsonNode budgetMoney_2 = jn.get("budgetmoney_2");
                        //项目更新
                        if (t != null && c != null) {
                            prjectids.add(Integer.valueOf(t.asText()));
                            WeeklyInfo weeklyInfo = new WeeklyInfo();
                            weeklyInfo.setProject_id(Integer.valueOf(t.asText()));
                            weeklyInfo.setContent(c.asText());
                            list.add(weeklyInfo);
                        }
                        if ((StringUtils.isEmpty(projectNumber.asText())) ||
                                (!org.apache.commons.lang3.StringUtils.isNumeric(t.asText())) ||
                                (!org.apache.commons.lang3.StringUtils.isNumeric(projectcustomerid.asText())) ||
                                (!org.apache.commons.lang3.StringUtils.isNumeric(projectownerId.asText())) ||
                                (!org.apache.commons.lang3.StringUtils.isNumeric(billsignplandate.asText()))) {
                            respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                            respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                            return response.toJsonObject();
                        } else {
                            ProjectEntity entity = new ProjectEntity();
                            entity.setId(Integer.parseInt(t.asText()));
                            entity.setProjectname(projectName.asText());
                            entity.setRate(Integer.parseInt(rates.asText()));
                            entity.setStep(Integer.parseInt(steps.asText()));
                            entity.setInfoprogress(infoProgress.asText());
                            entity.setDifficultyhelp(difficultyHelp.asText());
                            entity.setCurrentactualmoney(new BigDecimal(currentactualMoney.asText()));
                            entity.setCurrentbudgetmoney(new BigDecimal(currentbudgetMoney.asText()));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(projectdate.asText()))
                                entity.setProjectdate(new Timestamp(Long.parseLong(projectdate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(capitaldate.asText()))
                                entity.setCapitaldate(new Timestamp(Long.parseLong(capitaldate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(capitalusingdate.asText()))
                                entity.setCapitalusingdate(new Timestamp(Long.parseLong(capitalusingdate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(bidingdocumentdate.asText()))
                                entity.setBidingdocumentdate(new Timestamp(Long.parseLong(bidingdocumentdate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(billsignplandate.asText()))
                                entity.setBillsignplandate(new Timestamp(Long.parseLong(billsignplandate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(billsignactualdate.asText()))
                                entity.setBillsignactualdate(new Timestamp(Long.parseLong(billsignactualdate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(commercialplandate.asText()))
                                entity.setCommercialplandate(new Timestamp(Long.parseLong(commercialplandate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(commercialactualdate.asText()))
                                entity.setCommercialactualdate(new Timestamp(Long.parseLong(commercialactualdate.asText())));
                            entity.setMajordomo(majordomo.asText());
                            entity.setDeputymajordomo(deputymajordomo.asText());
                            entity.setProjectownerid(Integer.parseInt(projectownerId.asText()));
                            entity.setProjectowner(projectOwner.asText());
                            entity.setProjectcustomerid(Integer.parseInt(projectcustomerid.asText()));
                            entity.setProjectcustomer(projectcustomer.asText());
                            entity.setCustomerabbreviation(customerabbreviation.asText());
                            entity.setProjectnumber(projectNumber.asText());
                            entity.setProjectabbreviation(projectabbreViation.asText());
                            entity.setTotalbudgetmoney(new BigDecimal(totalbudgetMoney.asText()));
                            entity.setBudgetmoney_1(new BigDecimal(budgetMoney_1.asText()));
                            entity.setBudgetmoney_2(new BigDecimal(budgetMoney_2.asText()));
                            projectEntities.add(entity);
                        }
                    }
                }
                Set<Integer> set = new HashSet<>(prjectids);
                if (set.size() < prjectids.size()) {
                    throw new RestException(ErrorCode.PROJECTNAME_EXIST,
                            ErrorMessageUtil.getMessage(ErrorCode.PROJECTNAME_EXIST));
                }

                AddWeeklyReq addWeeklyReq = new AddWeeklyReq();
                addWeeklyReq.setStarttime(Long.valueOf(starttime));
                addWeeklyReq.setEndtime(Long.valueOf(endtime));
                addWeeklyReq.setName(name);
                addWeeklyReq.setSales(new BigDecimal(sales));
                addWeeklyReq.setUser_id(Long.valueOf(loginid));
                addWeeklyReq.setWeekinfos(list);
                addWeeklyReq.setProjectEntitys(projectEntities);
                Integer result = weeklyService.addWeekly(addWeeklyReq, session);
                if (result == 0) {
                    respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
                } else {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                }

            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 查询某个周报的具体信息
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getweeklyinfo")
    public @ResponseBody
    JsonNode getWeeklyInfo(@RequestBody JsonNode parameters) {
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
                WeeklyInfoResponse weeklyResponse = weeklyService.getWeeklyInfo(Long.valueOf(loginid), Integer.valueOf(id));
                Map<String, Object> map = new HashMap<>();
                map.put("weeklyResponse", weeklyResponse);
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
     * 修改周报信息
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/updateweeklyinfo")
    public @ResponseBody
    JsonNode updateWeeklyInfo(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String loginid = command.getParameter("loginid");
                String id = command.getParameter("id");
                String sales = command.getParameter("sales");
                String starttime = command.getParameter("starttime");
                String endtime = command.getParameter("endtime");
                JsonNode jsonNode = command.getParameterAsJsonNode("weekinfos");
                if (StringUtils.isEmpty(loginid) || StringUtils.isEmpty(id) ||
                        StringUtils.isEmpty(starttime) || StringUtils.isEmpty(endtime)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                Weekly weekly = new Weekly();
                weekly.setId(Integer.valueOf(id));
                weekly.setSales(new BigDecimal(sales));
                weekly.setUser_id(Long.valueOf(loginid));
                List<WeeklyInfo> list = new ArrayList<>();
                List<ProjectEntity> projectEntities = new ArrayList<ProjectEntity>();
                List<ProjectStatisticsEntity> projectStatisticsEntities = new ArrayList<ProjectStatisticsEntity>();
                if (jsonNode instanceof ArrayNode) {
                    ArrayNode an = (ArrayNode) jsonNode;
                    for (int i = 0; i < an.size(); i++) {
                        JsonNode jn = an.get(i);
                        JsonNode t = jn.get("project_id");
                        JsonNode c = jn.get("content");
                        JsonNode w = jn.get("weekly_id");
                        //项目跟新
                        JsonNode projectName = jn.get("projectname");
                        JsonNode weekly_id = jn.get("weekly_id");
                        JsonNode rates = jn.get("rate");
                        JsonNode steps = jn.get("step");
                        JsonNode infoProgress = jn.get("infoprogress");
                        JsonNode difficultyHelp = jn.get("difficultyhelp");
                        JsonNode currentbudgetMoney = jn.get("currentbudgetmoney");
                        JsonNode currentactualMoney = jn.get("currentactualmoney");
                        JsonNode projectdate = jn.get("projectdate");
                        JsonNode capitaldate = jn.get("capitaldate");
                        JsonNode capitalusingdate = jn.get("capitalusingdate");
                        JsonNode bidingdocumentdate = jn.get("bidingdocumentdate");
                        JsonNode billsignplandate = jn.get("billsignplandate");
                        JsonNode billsignactualdate = jn.get("billsignactualdate");
                        JsonNode commercialplandate = jn.get("commercialplandate");
                        JsonNode commercialactualdate = jn.get("commercialactualdate");
                        JsonNode majordomo = jn.get("majordomo");
                        JsonNode deputymajordomo = jn.get("deputymajordomo");
                        JsonNode projectcustomerid = jn.get("projectcustomerid");
                        JsonNode projectcustomer = jn.get("projectcustomer");
                        JsonNode customerabbreviation = jn.get("customerabbreviation");
                        JsonNode projectNumber = jn.get("projectnumber");
                        JsonNode projectabbreViation = jn.get("projectabbreviation");
                        JsonNode projectownerId = jn.get("projectownerid");
                        JsonNode projectOwner = jn.get("projectowner");
                        JsonNode totalbudgetMoney = jn.get("totalbudgetmoney");
                        JsonNode budgetMoney_1 = jn.get("budgetmoney_1");
                        JsonNode budgetMoney_2 = jn.get("budgetmoney_2");
                        //项目更新
                        if (t != null && c != null && w != null) {
                            WeeklyInfo weeklyInfo = new WeeklyInfo();
                            weeklyInfo.setProject_id(Integer.valueOf(t.asText()));
                            weeklyInfo.setContent(c.asText());
                            weeklyInfo.setWeekly_id(Integer.valueOf(w.asText()));
                            list.add(weeklyInfo);
                        }
                        if (
                                (StringUtils.isEmpty(projectNumber.asText())) ||
                                        (!org.apache.commons.lang3.StringUtils.isNumeric(t.asText())) ||
                                        (!org.apache.commons.lang3.StringUtils.isNumeric(projectcustomerid.asText())) ||
                                        (!org.apache.commons.lang3.StringUtils.isNumeric(projectownerId.asText())) ||
                                        (!org.apache.commons.lang3.StringUtils.isNumeric(billsignplandate.asText()))) {
                            respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                            respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                            return response.toJsonObject();
                        } else {
                            ProjectEntity entity = new ProjectEntity();
                            entity.setId(Integer.parseInt(t.asText()));
                            entity.setProjectname(projectName.asText());
                            entity.setRate(Integer.parseInt(rates.asText()));
                            entity.setStep(Integer.parseInt(steps.asText()));
                            entity.setInfoprogress(infoProgress.asText());
                            entity.setDifficultyhelp(difficultyHelp.asText());
                            entity.setCurrentactualmoney(new BigDecimal(currentactualMoney.asText()));
                            entity.setCurrentbudgetmoney(new BigDecimal(currentbudgetMoney.asText()));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(projectdate.asText()))
                                entity.setProjectdate(new Timestamp(Long.parseLong(projectdate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(capitaldate.asText()))
                                entity.setCapitaldate(new Timestamp(Long.parseLong(capitaldate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(capitalusingdate.asText()))
                                entity.setCapitalusingdate(new Timestamp(Long.parseLong(capitalusingdate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(bidingdocumentdate.asText()))
                                entity.setBidingdocumentdate(new Timestamp(Long.parseLong(bidingdocumentdate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(billsignplandate.asText()))
                                entity.setBillsignplandate(new Timestamp(Long.parseLong(billsignplandate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(billsignactualdate.asText()))
                                entity.setBillsignactualdate(new Timestamp(Long.parseLong(billsignactualdate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(commercialplandate.asText()))
                                entity.setCommercialplandate(new Timestamp(Long.parseLong(commercialplandate.asText())));
                            if (org.apache.commons.lang3.StringUtils.isNumeric(commercialactualdate.asText()))
                                entity.setCommercialactualdate(new Timestamp(Long.parseLong(commercialactualdate.asText())));
                            entity.setMajordomo(majordomo.asText());
                            entity.setDeputymajordomo(deputymajordomo.asText());
                            entity.setProjectownerid(Integer.parseInt(projectownerId.asText()));
                            entity.setProjectowner(projectOwner.asText());
                            entity.setProjectcustomerid(Integer.parseInt(projectcustomerid.asText()));
                            entity.setProjectcustomer(projectcustomer.asText());
                            entity.setCustomerabbreviation(customerabbreviation.asText());
                            entity.setProjectnumber(projectNumber.asText());
                            entity.setProjectabbreviation(projectabbreViation.asText());
                            entity.setTotalbudgetmoney(new BigDecimal(totalbudgetMoney.asText()));
                            entity.setBudgetmoney_1(new BigDecimal(budgetMoney_1.asText()));
                            entity.setBudgetmoney_2(new BigDecimal(budgetMoney_2.asText()));
                            projectEntities.add(entity);
                            ProjectStatisticsEntity projectStatisticsEntity = new ProjectStatisticsEntity();
                            Date date = entity.getBillsignplandate();
                            projectStatisticsEntity.setUserid(entity.getProjectownerid());
                            projectStatisticsEntity.setMoney(entity.getCurrentbudgetmoney());
                            projectStatisticsEntity.setYear_(Integer.valueOf(DateUtil.getInstance().getYear(date)));
                            projectStatisticsEntity.setMonth_(Integer.valueOf(DateUtil.getInstance().getMonth(date)));
                            projectStatisticsEntity.setProjectid(entity.getId());
                            projectStatisticsEntity.setStatus(entity.getStep());
                            projectStatisticsEntities.add(projectStatisticsEntity);
                        }

                    }
                }
                Long startTime = Long.valueOf(starttime);
                Long endTime = Long.valueOf(endtime);
                weeklyService.updateWeeklyInfo(weekly, projectEntities, startTime, endTime, list, projectStatisticsEntities);
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    @RequestMapping("/deleteweeklyinfo")
    public @ResponseBody
    JsonNode deleteWeeklyInfo(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String loginid = command.getParameter("loginid");
                String id = command.getParameter("id");
                String project_id = command.getParameter("project_id");
                if (StringUtils.isEmpty(loginid) || StringUtils.isEmpty(id) || StringUtils.isEmpty(project_id)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                Map<String, Integer> map = new HashMap<>();
                map.put("weekly_id", Integer.valueOf(id));
                map.put("project_id", Integer.valueOf(project_id));
                weeklyService.deleteWeeklyInfo(Long.valueOf(loginid), map);
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 周报列表需要过滤的基础数据
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getweeklybaseinfo")
    public @ResponseBody
    JsonNode getweeklybaseinfo(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String loginid = command.getParameter("loginid");
                String username = command.getParameter("username");
                String name = command.getParameter("name");
                if (StringUtils.isEmpty(loginid)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
//                if(StringUtils.isEmpty(username) ){
//                    username=null;
//
//                }
//                if(StringUtils.isEmpty(name)){
//                    name=null;
//                }
                WeeklyBaseInfo weeklyBaseInfo = weeklyService.getWeeklyBaseInfos(username, name);
                Map<String, Object> map = new HashMap<>();
                map.put("weeklyBaseInfo", weeklyBaseInfo);
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
