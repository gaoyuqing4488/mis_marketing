package com.misrobot.mismarketing.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.entity.*;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.mismarketing.dao.ProjectHistoryInfoResponse;
import com.misrobot.mismarketing.pojo.mismarketing.dao.ProjectInfoResponse;
import com.misrobot.mismarketing.pojo.project.ProjectByWeekly;
import com.misrobot.mismarketing.service.ProjectService;
import com.misrobot.mismarketing.util.DateUtil;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.JsonUtil;
import com.misrobot.mismarketing.util.TokenUtil;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Created by CHJ on 2017/8/1.
 */
@Controller
public @RequestMapping("/project")
class ProjectController {

    @Autowired
    private ProjectService mProjectService;

    @RequestMapping("/createnewproject")
    public @ResponseBody
    JsonNode createNewProject(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            //
            String cmd = command.getParameter("command");
            String projectname = command.getParameter("projectname");
            String rate = command.getParameter("rate");
            String step = command.getParameter("step");
            String infoprogress = command.getParameter("infoprogress");
            String difficultyhelp = command.getParameter("difficultyhelp");
            String currentbudgetmoney = command.getParameter("currentbudgetmoney");
            String currentactualmoney = command.getParameter("currentactualmoney");
            String projectdate = command.getParameter("projectdate");
            String capitaldate = command.getParameter("capitaldate");
            String capitalusingdate = command.getParameter("capitalusingdate");
            String bidingdocumentdate = command.getParameter("bidingdocumentdate");
            String billsignplandate = command.getParameter("billsignplandate");
            String billsignactualdate = command.getParameter("billsignactualdate");
            String commercialplandate = command.getParameter("commercialplandate");
            String commercialactualdate = command.getParameter("commercialactualdate");
            String majordomo = command.getParameter("majordomo");
            String deputymajordomo = command.getParameter("deputymajordomo");
            String projectcustomerid = command.getParameter("projectcustomerid");
            String projectcustomer = command.getParameter("projectcustomer");
            String customerabbreviation = command.getParameter("customerabbreviation");
            String projectnumber = command.getParameter("projectnumber");
            String projectabbreviation = command.getParameter("projectabbreviation");
            String projectownerid = command.getParameter("projectownerid");
            String projectowner = command.getParameter("projectowner");
            String totalbudgetmoney = command.getParameter("totalbudgetmoney");
            String budgetmoney_1 = command.getParameter("budgetmoney_1");
            String budgetmoney_2 = command.getParameter("budgetmoney_2");

            if (StringUtils.isEmpty(cmd) || StringUtils.isEmpty(projectname)
                    || (!isNumeric(rate))
                    || (!isNumeric(step))
                    || (isEmpty(projectnumber))
                    //|| (!isNumeric(projectnumber))
                    || (!isNumeric(projectownerid))
                    || (!isNumeric(projectcustomerid))
                    || (!isNumeric(billsignplandate))) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }
            if (!TokenUtil.checkLoginInfo(Long.parseLong(projectownerid), session)) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.SESSION_INVALID));
                restCmd.setStatusDescription("session is invalid!");
                return response.toJsonObject();
            }

            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setProjectname(projectname);
            projectEntity.setRate(Integer.parseInt(rate));
            projectEntity.setStep(Integer.parseInt(step));
            projectEntity.setInfoprogress(infoprogress);
            projectEntity.setDifficultyhelp(difficultyhelp);
            projectEntity.setCurrentbudgetmoney(new BigDecimal(currentbudgetmoney));
            projectEntity.setCurrentactualmoney(new BigDecimal(currentactualmoney));
            if (isNumeric(projectdate))
                projectEntity.setProjectdate(new Timestamp(Long.parseLong(projectdate)));
            if (isNumeric(capitaldate))
                projectEntity.setCapitaldate(new Timestamp(Long.parseLong(capitaldate)));
            if (isNumeric(capitalusingdate))
                projectEntity.setCapitalusingdate(new Timestamp(Long.parseLong(capitalusingdate)));
            if (isNumeric(bidingdocumentdate))
                projectEntity.setBidingdocumentdate(new Timestamp(Long.parseLong(bidingdocumentdate)));
            if (isNumeric(billsignplandate))
                projectEntity.setBillsignplandate(new Timestamp(Long.parseLong(billsignplandate)));
            if (isNumeric(billsignactualdate))
                projectEntity.setBillsignactualdate(new Timestamp(Long.parseLong(billsignactualdate)));
            if (isNumeric(commercialplandate))
                projectEntity.setCommercialplandate(new Timestamp(Long.parseLong(commercialplandate)));
            if (isNumeric(commercialactualdate))
                projectEntity.setCommercialactualdate(new Timestamp(Long.parseLong(commercialactualdate)));
            projectEntity.setMajordomo(majordomo);
            projectEntity.setDeputymajordomo(deputymajordomo);
            projectEntity.setProjectownerid(Integer.parseInt(projectownerid));
            projectEntity.setProjectcustomerid(Integer.parseInt(projectcustomerid));
            projectEntity.setProjectcustomer(projectcustomer);
            projectEntity.setCustomerabbreviation(customerabbreviation);
            projectEntity.setProjectnumber(projectnumber);
            projectEntity.setProjectabbreviation(projectabbreviation);
            projectEntity.setProjectowner(projectowner);
            projectEntity.setTotalbudgetmoney(new BigDecimal(totalbudgetmoney));
            projectEntity.setBudgetmoney_1(new BigDecimal(budgetmoney_1));
            projectEntity.setBudgetmoney_2(new BigDecimal(budgetmoney_2));
            //
            List<ProjectEntity> projects = mProjectService.findByProjectNumber(projectnumber.trim());
            if (projects != null && (!projects.isEmpty())) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.PROJECTNAME_EXIST));
                restCmd.setStatusDescription("project is exist!");
                return response.toJsonObject();
            }
            int count = 0;
            try {
                count = mProjectService.createNewProject(projectEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (count > 0) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("projectid", projectEntity.getId());
                restCmd.setParameter(jsonObject.toString());
                //restCmd.setParameter(JsonUtil.getJSONString(response));
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
                restCmd.setStatusDescription("create new project success.");
            } else {
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.FAIL));
                restCmd.setStatusDescription("create new project failure.");
            }
        }
        return response.toJsonObject();
    }

    @RequestMapping("/getprojectscount")
    public @ResponseBody
    JsonNode getProjectsCount(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            //
            String cmd = command.getParameter("command");
            String projectid = command.getParameter("projectid");
            String userid = command.getParameter("userid");
            if (StringUtils.isEmpty(cmd)) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }
            int count = 0;
            if (!StringUtils.isEmpty(projectid)) {
                count = mProjectService.getProjectsCountByPrimaryKey(Integer.parseInt(projectid));
            } else { //find all projects.
                if ((!StringUtils.isEmpty(userid)) && isNumeric(userid)) {
                    count = mProjectService.getProjectsCountByUserID(Integer.valueOf(userid));
                } else {
                    count = mProjectService.getAllProjectsCount();
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode res = mapper.createObjectNode();
            res.put("projectscount", count);
            //
            restCmd.setParameter(res);
            restCmd.setCommand(cmd);
            restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
            restCmd.setStatusDescription("query projects success.");
        }
        return response.toJsonObject();
    }

    @RequestMapping("/queryprojects")
    public @ResponseBody
    JsonNode queryProjects(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            //
            String cmd = command.getParameter("command");
            String projectid = command.getParameter("projectid");
            String userid = command.getParameter("userid");
            String page = command.getParameter("page");
            String pagesize = command.getParameter("pagesize");
            if (StringUtils.isEmpty(cmd) ||
                    !isNumeric(page) ||
                    !isNumeric(pagesize) ||
                    (Integer.parseInt(pagesize) <= 0) || (Integer.parseInt(page) <= 0)) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }
            int offset = (Integer.parseInt(page) - 1) * Integer.parseInt(pagesize);
            List<ProjectEntity> resultList;
            if (!StringUtils.isEmpty(projectid)) { //find identify project.
                resultList = mProjectService.selectByPrimaryKey(Integer.parseInt(projectid));
            } else { //find all projects.
                if ((!StringUtils.isEmpty(userid)) && isNumeric(userid)) {
                    resultList = mProjectService.findAllProjectsByUserID(Integer.valueOf(userid), offset, Integer.parseInt(pagesize));
                } else {
                    resultList = mProjectService.findAllProjects(offset, Integer.parseInt(pagesize));
                }
            }
            ProjectInfoResponse res = new ProjectInfoResponse();
            for (ProjectEntity entity : resultList) {
                ProjectInfoResponse.ProjectInfoUnit projectInfoUnit = new ProjectInfoResponse.ProjectInfoUnit();
                res.getQueryprojectlist().add(projectInfoUnit.init(entity));
            }
            //
            restCmd.setParameter(JsonUtil.getJSONString(res));
            restCmd.setCommand(cmd);
            restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
            restCmd.setStatusDescription("query projects success.");
        }
        return response.toJsonObject();
    }

    @RequestMapping("/querycustomerprojects")
    public @ResponseBody
    JsonNode queryCustomerProjects(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            //
            String cmd = command.getParameter("command");
            String customerid = command.getParameter("customerid");
            String page = command.getParameter("page");
            String pagesize = command.getParameter("pagesize");
            if (StringUtils.isEmpty(cmd) ||
                    (!isNumeric(customerid)) ||
                    !isNumeric(page) ||
                    !isNumeric(pagesize) ||
                    Integer.parseInt(page) <= 0 ||
                    Integer.parseInt(pagesize) <= 0) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }
            int offset = (Integer.parseInt(page) - 1) * Integer.parseInt(pagesize);
            List<ProjectEntity> resultList;
            resultList = mProjectService.findAllProjectsByCustomerID(Integer.parseInt(customerid), offset, Integer.parseInt(pagesize));
            ProjectInfoResponse res = new ProjectInfoResponse();
            for (ProjectEntity entity : resultList) {
                ProjectInfoResponse.ProjectInfoUnit projectInfoUnit = new ProjectInfoResponse.ProjectInfoUnit();
                res.getQueryprojectlist().add(projectInfoUnit.init(entity));
            }
            //
            restCmd.setParameter(JsonUtil.getJSONString(res));
            restCmd.setCommand(cmd);
            restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
            restCmd.setStatusDescription("query projects success.");
        }
        return response.toJsonObject();
    }

    @RequestMapping("/getmyproject")
    public @ResponseBody
    JsonNode getMyProjects(@RequestBody JsonNode parameters) {
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
                Map<String, Long> map = new HashMap<>();
                map.put("userid", Long.valueOf(loginid));
                List<ProjectByWeekly> projectByWeeklies = mProjectService.getMyProject(map);
                Map<String, Object> maplist = new HashMap<>();
                maplist.put("myprojects", projectByWeeklies);
                respCmd.setParameter(JsonUtil.getJSONString(maplist));
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    @RequestMapping("/updateproject")
    public @ResponseBody
    JsonNode updateProject(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            //
            String cmd = command.getParameter("command");
            String projectid = command.getParameter("projectid");
            String projectname = command.getParameter("projectname");
            String rate = command.getParameter("rate");
            String step = command.getParameter("step");
            String infoprogress = command.getParameter("infoprogress");
            String difficultyhelp = command.getParameter("difficultyhelp");
            String currentbudgetmoney = command.getParameter("currentbudgetmoney");
            String currentactualmoney = command.getParameter("currentactualmoney");
            String projectdate = command.getParameter("projectdate");
            String capitaldate = command.getParameter("capitaldate");
            String capitalusingdate = command.getParameter("capitalusingdate");
            String bidingdocumentdate = command.getParameter("bidingdocumentdate");
            String beginbudgetdate = command.getParameter("beginbudgetdate");
            String billsignplandate = command.getParameter("billsignplandate");
            String billsignactualdate = command.getParameter("billsignactualdate");
            String commercialplandate = command.getParameter("commercialplandate");
            String commercialactualdate = command.getParameter("commercialactualdate");
            String majordomo = command.getParameter("majordomo");
            String deputymajordomo = command.getParameter("deputymajordomo");
            String projectcustomerid = command.getParameter("projectcustomerid");
            String projectcustomer = command.getParameter("projectcustomer");
            String customerabbreviation = command.getParameter("customerabbreviation");
            String projectnumber = command.getParameter("projectnumber");
            String projectabbreviation = command.getParameter("projectabbreviation");
            String projectownerid = command.getParameter("projectownerid");
            String projectowner = command.getParameter("projectowner");
            String totalbudgetmoney = command.getParameter("totalbudgetmoney");
            String budgetmoney_1 = command.getParameter("budgetmoney_1");
            String budgetmoney_2 = command.getParameter("budgetmoney_2");
            if (StringUtils.isEmpty(cmd) ||
                    (isEmpty(projectnumber)) ||
                    (!isNumeric(projectid)) ||
                    (!isNumeric(projectcustomerid)) ||
                    (!isNumeric(projectownerid)) ||
                    (!isNumeric(beginbudgetdate)) ||
                    (!isNumeric(billsignplandate))) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }
            if (!TokenUtil.checkLoginInfo(Long.parseLong(projectownerid), session)) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.SESSION_INVALID));
                restCmd.setStatusDescription("session is invalid!");
                return response.toJsonObject();
            }
            //update project info...
            ProjectEntity entity = new ProjectEntity();
            entity.setId(Integer.parseInt(projectid));
            entity.setProjectname(projectname);
            entity.setRate(Integer.parseInt(rate));
            entity.setStep(Integer.parseInt(step));
            entity.setInfoprogress(infoprogress);
            entity.setDifficultyhelp(difficultyhelp);
            entity.setCurrentactualmoney(new BigDecimal(currentactualmoney));
            entity.setCurrentbudgetmoney(new BigDecimal(currentbudgetmoney));
//            entity.setProjectdate(new Timestamp(Long.parseLong(projectdate)));
//            entity.setCapitaldate(new Timestamp(Long.parseLong(capitaldate)));
//            entity.setCapitalusingdate(new Timestamp(Long.parseLong(capitalusingdate)));
//            entity.setBidingdocumentdate(new Timestamp(Long.parseLong(bidingdocumentdate)));
//            entity.setBillsignplandate(new Timestamp(Long.parseLong(billsignplandate)));
//            entity.setBillsignactualdate(new Timestamp(Long.parseLong(billsignactualdate)));
//            entity.setCommercialplandate(new Timestamp(Long.parseLong(commercialplandate)));
//            entity.setCommercialactualdate(new Timestamp(Long.parseLong(commercialactualdate)));
            if (isNumeric(projectdate))
                entity.setProjectdate(new Timestamp(Long.parseLong(projectdate)));
            if (isNumeric(capitaldate))
                entity.setCapitaldate(new Timestamp(Long.parseLong(capitaldate)));
            if (isNumeric(capitalusingdate))
                entity.setCapitalusingdate(new Timestamp(Long.parseLong(capitalusingdate)));
            if (isNumeric(bidingdocumentdate))
                entity.setBidingdocumentdate(new Timestamp(Long.parseLong(bidingdocumentdate)));
            if (isNumeric(billsignplandate))
                entity.setBillsignplandate(new Timestamp(Long.parseLong(billsignplandate)));
            if (isNumeric(billsignactualdate))
                entity.setBillsignactualdate(new Timestamp(Long.parseLong(billsignactualdate)));
            if (isNumeric(commercialplandate))
                entity.setCommercialplandate(new Timestamp(Long.parseLong(commercialplandate)));
            if (isNumeric(commercialactualdate))
                entity.setCommercialactualdate(new Timestamp(Long.parseLong(commercialactualdate)));
            entity.setMajordomo(majordomo);
            entity.setDeputymajordomo(deputymajordomo);
            entity.setProjectownerid(Integer.parseInt(projectownerid));
            entity.setProjectowner(projectowner);
            entity.setProjectcustomerid(Integer.parseInt(projectcustomerid));
            entity.setProjectcustomer(projectcustomer);
            entity.setCustomerabbreviation(customerabbreviation);
            entity.setProjectnumber(projectnumber);
            entity.setProjectabbreviation(projectabbreviation);
            entity.setTotalbudgetmoney(new BigDecimal(totalbudgetmoney));
            entity.setBudgetmoney_1(new BigDecimal(budgetmoney_1));
            entity.setBudgetmoney_2(new BigDecimal(budgetmoney_2));
            try {
                Date createtime = new Timestamp(Long.parseLong(beginbudgetdate));
                int year = DateUtil.getInstance().getYearValue(createtime);
                mProjectService.updateByPrimaryKey(year, entity);
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
                restCmd.setStatusDescription("update project info success.");
            } catch (Exception e) {
                e.printStackTrace();
                restCmd.setCommand(cmd);
                restCmd.setStatusCode(Integer.valueOf(Constants.FAIL));
                restCmd.setStatusDescription("update project info failed.");
            }
        }
        return response.toJsonObject();
    }

    @RequestMapping("/queryprojectmodifyhistory")
    public @ResponseBody
    JsonNode queryProjectModifyHistory(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            //
            String cmd = command.getParameter("command");
            String projectid = command.getParameter("projectid");
            if (StringUtils.isEmpty(cmd) || (!isNumeric(projectid))) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }
            List<ProjectHistoryEntity> projectHistoryEntityList = mProjectService.queryAllRecordsByProjectID(Integer.valueOf(projectid));
            //
            ProjectHistoryInfoResponse<ProjectHistoryInfoResponse.ProjectHistoryInfoUnit> res = new ProjectHistoryInfoResponse<>();
            for (ProjectHistoryEntity entity : projectHistoryEntityList) {
                ProjectHistoryInfoResponse.ProjectHistoryInfoUnit projectHistoryInfoUnit = new ProjectHistoryInfoResponse.ProjectHistoryInfoUnit();
                projectHistoryInfoUnit.setTitle(entity.getTitle());
                projectHistoryInfoUnit.setModifycontent(entity.getModifycontent());
                projectHistoryInfoUnit.setModifydate(entity.getModifydate());
                projectHistoryInfoUnit.setProjectid(entity.getProjectid());
                res.getQueryprojecthistorylist().add(projectHistoryInfoUnit);
            }
            restCmd.setParameter(JsonUtil.getJSONString(res));
            restCmd.setCommand(cmd);
            restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
            restCmd.setStatusDescription("query projects success.");
        }
        return response.toJsonObject();
    }

    @RequestMapping("/deleteproject")
    public @ResponseBody
    JsonNode deleteProject(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand restCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(restCmd);
            //
            String cmd = command.getParameter("command");
            String projectid = command.getParameter("projectid");
            String userid = command.getParameter("userid");
            if (StringUtils.isEmpty(cmd) ||
                    (!isNumeric(projectid)) ||
                    (!isNumeric(userid))) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                restCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                return response.toJsonObject();
            }
            if (!TokenUtil.checkLoginInfo(Long.parseLong(userid), session)) {
                restCmd.setStatusCode(Integer.valueOf(ErrorCode.SESSION_INVALID));
                restCmd.setStatusDescription("session is invalid!");
                return response.toJsonObject();
            }
            mProjectService.deleteByPrimaryKey(Integer.parseInt(projectid));
            restCmd.setCommand(cmd);
            restCmd.setStatusCode(Integer.valueOf(Constants.SUCCESS));
            restCmd.setStatusDescription("delete projects success.");
        }
        return response.toJsonObject();
    }

}
