package com.misrobot.mismarketing.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.misrobot.mismarketing.base.RestCommand;
import com.misrobot.mismarketing.base.RestRequest;
import com.misrobot.mismarketing.base.RestResponse;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.pojo.enroll.AddUserRes;
import com.misrobot.mismarketing.pojo.enroll.User;
import com.misrobot.mismarketing.service.UserService;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by GYQ on 2018/1/9.
 */
@Controller
public @RequestMapping("/user")
class UseController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/adduser")
    public @ResponseBody
    JsonNode addUser(@RequestBody JsonNode parameters) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String username = command.getParameter("username");
                String pwd = command.getParameter("pwd");
                String nickname = command.getParameter("nickname");
                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(pwd)|| StringUtils.isEmpty(nickname)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                User user = new User();
                user.setUsername(username);
                user.setPwd(pwd);
                user.setCreatetime(new Date());
                user.setNickname(nickname);
                AddUserRes addUserRes = userService.addUser(user);
                respCmd.setParameter(JsonUtil.getJSONString(addUserRes));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response.toJsonObject();
    }

    /**
     * 用户登陆
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/loginuser")
    public @ResponseBody
    JsonNode loginUser(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String username = command.getParameter("username");
                String pwd = command.getParameter("pwd");
                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(pwd)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                User user = new User();
                user.setUsername(username);
                user.setPwd(pwd);
                AddUserRes addUserRes = userService.loginUserInfo(user, session);
                respCmd.setParameter(JsonUtil.getJSONString(addUserRes));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response.toJsonObject();
    }


}
