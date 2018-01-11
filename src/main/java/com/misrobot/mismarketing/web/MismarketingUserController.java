package com.misrobot.mismarketing.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.*;
import com.misrobot.mismarketing.pojo.mismarketing.dao.*;
import com.misrobot.mismarketing.pojo.weekly.WeeklyUserInfo;
import com.misrobot.mismarketing.service.MismarketingAdminLoginPadService;
import com.misrobot.mismarketing.service.MismarketingAdminUserService;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.JsonUtil;
import com.misrobot.mismarketing.util.TokenUtil;
import com.misrobot.mismarketing.base.RestCommand;
import com.misrobot.mismarketing.base.RestRequest;
import com.misrobot.mismarketing.base.RestResponse;
import org.apache.commons.lang3.StringUtils;
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
 * Created by gao on 2017/5/11.
 */
@Controller
public @RequestMapping("/mismarketing")
class MismarketingUserController {

    @Autowired
    private MismarketingAdminLoginPadService mismarketingLoginPadService;

    @Autowired
    private MismarketingAdminUserService mismarketingUserService;

    /**
     * 营销后台管理登陆
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/login")
    public @ResponseBody
    JsonNode loginAdminUser(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            AdminUserLoginReq adminUserLoginReq = new AdminUserLoginReq();
            try {
                String loginname = command.getParameter("loginname");
                String pwd = command.getParameter("pwd");
                String commands = command.getParameter("command");
                if (StringUtils.isEmpty(loginname) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(commands)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                adminUserLoginReq.setCommand(commands);
                adminUserLoginReq.setLoginname(loginname);
                adminUserLoginReq.setPwd(pwd);
                MismarketingAdminUserPadRes mismarketingAdminUserPadRes = mismarketingLoginPadService.getLoginInfo(adminUserLoginReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(mismarketingAdminUserPadRes));
//                Map<String, String> loginresult = userService.login(loginname, pwd);
//                respCmd.setStatusCode(code);
//                respCmd.setParameter("sessionid", loginresult.get("sessionid"));
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        System.out.print("=======================================" + response.toJsonObject());
        return response.toJsonObject();
    }

    @RequestMapping("/logout")
    public @ResponseBody
    JsonNode logout(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        int code = Constants.ERRCODE_SUCCESS;
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            QueryUserReq queryUserReq = new QueryUserReq();
            try {
                String commands = command.getParameter("command");
                String loginuserid = command.getParameter("loginuserid");
                if (StringUtils.isEmpty(commands) || StringUtils.isEmpty(loginuserid)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                TokenUtil.removeToken(Long.parseLong(loginuserid));
                respCmd.setStatusCode(code);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 营销pad登陆
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getmismarketinguserinfo")
    public @ResponseBody
    JsonNode getYxAdminUserInfo(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            AdminUserLoginReq adminUserLoginReq = new AdminUserLoginReq();
            try {
                String loginname = command.getParameter("loginname");
                String pwd = command.getParameter("pwd");
                String commands = command.getParameter("command");

                if (StringUtils.isEmpty(loginname) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(commands)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                adminUserLoginReq.setCommand(commands);
                adminUserLoginReq.setLoginname(loginname);
                adminUserLoginReq.setPwd(pwd);
                MismarketingAdminUserPadRes mismarketingAdminUserPadRes = mismarketingLoginPadService.getLoginInfo(adminUserLoginReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(mismarketingAdminUserPadRes));
                //respCmd.setStatusCode(code);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }

        return response.toJsonObject();
    }

    /**
     * pad修改用户密码
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/updateinfo")
    public @ResponseBody
    JsonNode updateInfo(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            UpdateAdminUserReq updateAdminUserReq = new UpdateAdminUserReq();
            try {
                String pwd = command.getParameter("pwd");
                String userid = command.getParameter("userid");
                String commands = command.getParameter("command");
                String isupdate = command.getParameter("isupdate");
                if (StringUtils.isEmpty(commands) || StringUtils.isEmpty(userid) || StringUtils.isEmpty(isupdate)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                updateAdminUserReq.setUserid(Long.parseLong(userid));
                if (!StringUtils.isEmpty(pwd)) {
                    updateAdminUserReq.setPwd(pwd);
                }
                if (!StringUtils.isEmpty(isupdate)) {
                    updateAdminUserReq.setIsupdate(isupdate);
                }
                BaseResponse baseResponse = mismarketingUserService.updateUser(updateAdminUserReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(baseResponse));
                respCmd.setCommand(commands);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 获取下载文件
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getfilepath")
    public @ResponseBody
    JsonNode getFilePath(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        int code = Constants.ERRCODE_SUCCESS;
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String commands = command.getParameter("command");
                if (StringUtils.isEmpty(commands)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                QueryFileRes queryFileRes = mismarketingLoginPadService.getFilePath();
                respCmd.setParameter(JsonUtil.getJSONString(queryFileRes));
                respCmd.setCommand(commands);
                respCmd.setStatusCode(code);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 获取营销后台管理用户列表
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getusersinfo")
    public @ResponseBody
    JsonNode getAllAdminUser(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        int code = Constants.ERRCODE_SUCCESS;
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            MismarketingAdminUserReq mismarketingAdminUserReq = new MismarketingAdminUserReq();
            try {
                String userid = command.getParameter("userid");
                String commands = command.getParameter("command");
                String requestpage = command.getParameter("requestpage");
                String sizeperpage = command.getParameter("sizeperpage");
                if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(commands)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                mismarketingAdminUserReq.setCommand(commands);
                mismarketingAdminUserReq.setUserid(Long.valueOf(userid));
                if (StringUtils.isNotEmpty(requestpage) && StringUtils.isNotEmpty(sizeperpage)) {
                    int start = (Integer.valueOf(requestpage) - 1) * Integer.valueOf(sizeperpage);
                    mismarketingAdminUserReq.setRequestpage(start);
                    mismarketingAdminUserReq.setSizeperpage(Integer.valueOf(sizeperpage));
                }
                MismarketingAdminUsersRes mismarketingAdminUsersRes = mismarketingUserService.getAlluserInfo(mismarketingAdminUserReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(mismarketingAdminUsersRes));
                respCmd.setStatusCode(code);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 获取营销后台管理用户具体信息
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getuserinfo")
    public @ResponseBody
    JsonNode getAdminUserInfo(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        int code = Constants.ERRCODE_SUCCESS;

        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            QueryUserReq queryUserReq = new QueryUserReq();
            try {
                String userid = command.getParameter("userid");
                String commands = command.getParameter("command");
                if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(commands)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                queryUserReq.setCommand(commands);
                queryUserReq.setUserid(Long.valueOf(userid));
                QueryUserRes queryUserRes = mismarketingUserService.getAdminUserById(queryUserReq);
                respCmd.setParameter(JsonUtil.getJSONString(queryUserRes));
                respCmd.setStatusCode(code);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 新增管理员信息
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/adduserinfo")
    public @ResponseBody
    JsonNode addAdminInfo(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            AddAdminUserReq addAdminUserReq = new AddAdminUserReq();
            try {
                String username = command.getParameter("username");
                String pwd = command.getParameter("pwd");
                String loginname = command.getParameter("loginname");
                String type = command.getParameter("type");
                String commands = command.getParameter("command");
                String loginuserid = command.getParameter("loginuserid");
                String districtid = command.getParameter("districtid");
                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(commands) || StringUtils.isEmpty(pwd) ||
                        StringUtils.isEmpty(loginname) || StringUtils.isEmpty(type) ||
                        StringUtils.isEmpty(loginuserid) || StringUtils.isEmpty(districtid)
                        ) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                addAdminUserReq.setLoginuserid(Long.valueOf(loginuserid));
                addAdminUserReq.setCommand(commands);
                addAdminUserReq.setPwd(pwd);
                addAdminUserReq.setLoginname(loginname);
                addAdminUserReq.setType(Integer.valueOf(type));
                addAdminUserReq.setUsername(username);
                addAdminUserReq.setDistrictid(Integer.valueOf(districtid));
                AddAdminUserRes addAdminUserRes = mismarketingUserService.saveAdminUser(addAdminUserReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(addAdminUserRes));
                //respCmd.setStatusCode(code);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 修改管理员用户信息
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/updateuserinfo")
    public @ResponseBody
    JsonNode updateAdminInfo(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            UpdateAdminUserReq updateAdminUserReq = new UpdateAdminUserReq();
            try {
                String username = command.getParameter("username");
                String pwd = command.getParameter("pwd");
                String userid = command.getParameter("userid");
                String type = command.getParameter("type");
                String commands = command.getParameter("command");
                String loginuserid = command.getParameter("loginuserid");
                String loginname = command.getParameter("loginname");
                String districtid = command.getParameter("districtid");
                if (StringUtils.isEmpty(commands) || StringUtils.isEmpty(userid) || StringUtils.isEmpty(loginuserid) ||
                        StringUtils.isEmpty(districtid)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                updateAdminUserReq.setUserid(Long.parseLong(userid));
                updateAdminUserReq.setLoginuserid(Long.valueOf(loginuserid));
                updateAdminUserReq.setDistrictid(Integer.valueOf(districtid));
                if (!StringUtils.isEmpty(username)) {
                    updateAdminUserReq.setUsername(username);
                }
                if (!StringUtils.isEmpty(pwd)) {
                    updateAdminUserReq.setPwd(pwd);

                }
                if (!StringUtils.isEmpty(type)) {
                    updateAdminUserReq.setType(Integer.valueOf(type));
                }
                if (!StringUtils.isEmpty(loginname)) {
                    updateAdminUserReq.setLoginname(loginname);
                }
                BaseResponse baseResponse = mismarketingUserService.updateAdminUser(updateAdminUserReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(baseResponse));
                respCmd.setCommand(commands);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 删除后台管理人员
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/deleteuser")
    public @ResponseBody
    JsonNode deleteAdminUser(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            QueryUserReq queryUserReq = new QueryUserReq();
            try {
                String userid = command.getParameter("userid");
                String commands = command.getParameter("command");
                String loginuserid = command.getParameter("loginuserid");
                if (StringUtils.isEmpty(commands) || StringUtils.isEmpty(userid) || StringUtils.isEmpty(loginuserid)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                queryUserReq.setLoginuserid(Long.valueOf(loginuserid));
                queryUserReq.setUserid(Long.parseLong(userid));
                BaseResponse baseResponse = mismarketingUserService.deleteAdminUser(queryUserReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(baseResponse));
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 查询待激活的用户信息
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/queryactivateduserinfo")
    public @ResponseBody
    JsonNode queryActivateUser(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            Map<String, Object> map = new HashMap<String, Object>();

            String username = command.getParameter("username");
            String begintime = command.getParameter("begintime");
            String endtime = command.getParameter("endtime");
            String type = command.getParameter("type");
            String status = command.getParameter("status");
            String activateDays = command.getParameter("activateDays");
            String requestpage = command.getParameter("requestpage");
            String sizeperpage = command.getParameter("sizeperpage");
            if (!StringUtils.isEmpty(username)) {
                map.put("username", username);
            }
            if (!StringUtils.isEmpty(begintime)) {
                map.put("begintime", begintime);
            }
            if (!StringUtils.isEmpty(endtime)) {
                map.put("endtime", endtime);
            }
            if (!StringUtils.isEmpty(type)) {
                map.put("type", Integer.valueOf(type));
            }
            if (!StringUtils.isEmpty(status)) {
                map.put("status", Integer.valueOf(status));
            }
            if (!StringUtils.isEmpty(activateDays)) {
                map.put("activateDays", Integer.valueOf(activateDays));
            }
            if (!StringUtils.isEmpty(requestpage) && !StringUtils.isEmpty(sizeperpage)) {
                int start = (Integer.valueOf(requestpage) - 1) * Integer.valueOf(sizeperpage);
                map.put("requestpage", start);
                map.put("sizeperpage", Integer.valueOf(sizeperpage));
            }
            try {
                QueryActivateUserRes list = mismarketingUserService.getActivateUsers(map);
                respCmd.setParameter(JsonUtil.getJSONString(list));
                respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 批量激活
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/activateusers")
    public @ResponseBody
    JsonNode updateActivate(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        int code = Constants.ERRCODE_SUCCESS;
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            //String id = command.getParameter("userid");
            String loginuserid = command.getParameter("loginuserid");
            List<String> list = new ArrayList<>();
            try {
                if (StringUtils.isEmpty(loginuserid)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                JsonNode jsonNode = command.getParameterAsJsonNode("useridlist");
                if (jsonNode instanceof ArrayNode) {
                    ArrayNode an = (ArrayNode) jsonNode;
                    for (int i = 0; i < an.size(); i++) {
                        JsonNode jn = an.get(i);
                        JsonNode t = jn.get("userid");
                        if (t != null) {
                            list.add(t.asText());
                        }
                    }
                }
                mismarketingUserService.updateActivateUser(list, loginuserid, session);
                respCmd.setStatusCode(code);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    /**
     * 获取文件大小
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/getfilesize")
    public @ResponseBody
    JsonNode getFileSize(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        int code = 0;

        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            QueryUserReq queryUserReq = new QueryUserReq();
            try {
                String filename = command.getParameter("filename");
                String commands = command.getParameter("command");
                if (StringUtils.isEmpty(commands)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                QueryFileSizeRes queryFileSizeRes = mismarketingLoginPadService.getLargeFileSize(filename);
                Long size = queryFileSizeRes.getFileSize();
                if (size == -1) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                respCmd.setParameter(JsonUtil.getJSONString(queryFileSizeRes));
                respCmd.setCommand(commands);
                respCmd.setStatusCode(code);
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        return response.toJsonObject();
    }

    @RequestMapping("/loginmismarketingsystemuser")
    public @ResponseBody
    JsonNode loginMisSystemUser(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        int code = 0;

        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            AdminUserLoginReq adminUserLoginReq = new AdminUserLoginReq();
            try {
                String loginname = command.getParameter("loginname");
                String pwd = command.getParameter("pwd");
                String commands = command.getParameter("command");
                String type = command.getParameter("type");
                if (StringUtils.isEmpty(loginname) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(commands) || StringUtils.isEmpty(type)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                adminUserLoginReq.setCommand(commands);
                adminUserLoginReq.setLoginname(loginname);
                adminUserLoginReq.setPwd(pwd);
                adminUserLoginReq.setType(Integer.valueOf(type));
                MismarketingAdminUserPadRes mismarketingAdminUserPadRes = mismarketingLoginPadService.getLoginInfos(adminUserLoginReq, session);
                respCmd.setParameter(JsonUtil.getJSONString(mismarketingAdminUserPadRes));
//                Map<String, String> loginresult = userService.login(loginname, pwd);
//                respCmd.setStatusCode(code);
//                respCmd.setParameter("sessionid", loginresult.get("sessionid"));
            } catch (RestException e) {
                respCmd.setStatusDescription(e.getMessage());
                respCmd.setStatusCode(e.getExceptionCode());
            }
        }
        System.out.print("=======================================" + response.toJsonObject());
        return response.toJsonObject();
    }

    /**
     * 获取所有的用户
     * @param parameters
     * @return
     */
    @RequestMapping("/getallusers")
    public @ResponseBody
    JsonNode getallusers(@RequestBody JsonNode parameters) {
                RestRequest request = new RestRequest(RestCommand.parse(parameters));
                RestResponse response = new RestResponse();
                int code = 0;
                for (RestCommand command : request.getRestCommands()) {
                    RestCommand respCmd = new RestCommand(command.getCommand());
                    response.getRestCommands().add(respCmd);
                    try{
                        String username = command.getParameter("username");
                        List<WeeklyUserInfo> list=mismarketingUserService.getAllUsers(username);
                        Map<String,Object> map=new HashMap<String,Object>();
                        map.put("list",list);
                        respCmd.setParameter(JsonUtil.getJSONString(map));
                        respCmd.setStatusCode(code);
                    }catch (RestException e) {
                        respCmd.setStatusDescription(e.getMessage());
                        respCmd.setStatusCode(e.getExceptionCode());
                    }
                }
                return response.toJsonObject();
            }

}

