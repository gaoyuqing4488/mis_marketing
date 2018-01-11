package com.misrobot.mismarketing.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.misrobot.mismarketing.base.RestCommand;
import com.misrobot.mismarketing.base.RestRequest;
import com.misrobot.mismarketing.base.RestResponse;
import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.pojo.enroll.Enroll;
import com.misrobot.mismarketing.service.EnrollService;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
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
public @RequestMapping("/enroll")
class EnrollController {

    @Autowired
    private EnrollService enrollService;

    /**
     * 添加报名
     *
     * @param parameters
     * @return
     */
    @RequestMapping("/addenroll")
    public @ResponseBody
    JsonNode addEnroll(@RequestBody JsonNode parameters, HttpSession session) {
        RestRequest request = new RestRequest(RestCommand.parse(parameters));
        RestResponse response = new RestResponse();
        for (RestCommand command : request.getRestCommands()) {
            RestCommand respCmd = new RestCommand(command.getCommand());
            response.getRestCommands().add(respCmd);
            try {
                String username = command.getParameter("username");
                String schoolname = command.getParameter("schoolname");
                String classname = command.getParameter("classname");
                String major = command.getParameter("major");
                String telephone = command.getParameter("telephone");
                String mail = command.getParameter("mail");
                String areaid = command.getParameter("areaid");
                String contestitem = command.getParameter("contestitem");
                String userid = command.getParameter("loginid");
                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(schoolname) || StringUtils.isEmpty(classname)
                        || StringUtils.isEmpty(major) || StringUtils.isEmpty(telephone) ||
                        StringUtils.isEmpty(mail) || StringUtils.isEmpty(areaid) ||
                        StringUtils.isEmpty(contestitem) || StringUtils.isEmpty(userid)) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                    return response.toJsonObject();
                }
                Enroll enroll = new Enroll();
                enroll.setAreaid(Integer.valueOf(areaid));
                enroll.setClassname(classname);
                enroll.setContestitem(contestitem);
                enroll.setCreatetime(new Date());
                enroll.setMail(mail);
                enroll.setMajor(major);
                enroll.setSchoolname(schoolname);
                enroll.setUserid(Integer.valueOf(userid));
                enroll.setTelephone(telephone);
                enroll.setUsername(username);
                Integer result = enrollService.addEnroll(enroll, session);
                if (result != 0 && result != 2) {
                    respCmd.setStatusCode(Constants.ERRCODE_SUCCESS);
                } else if (result == 2) {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.ALREADY_SIGN_UP));
                    respCmd.setStatusDescription(ErrorMessageUtil.getMessage(ErrorCode.ALREADY_SIGN_UP));
                } else {
                    respCmd.setStatusCode(Integer.valueOf(ErrorCode.REQUEST_PARAMETER_EXCEPTION));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response.toJsonObject();
    }
}
