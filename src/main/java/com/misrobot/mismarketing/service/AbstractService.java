package com.misrobot.mismarketing.service;


import com.misrobot.mismarketing.constants.ErrorCode;
import com.misrobot.mismarketing.util.HttpPostClient;
import com.misrobot.mismarketing.base.RestCommand;
import com.misrobot.mismarketing.base.RestRequest;
import com.misrobot.mismarketing.base.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by susun on 2016/10/15.
 */
public abstract class AbstractService {
    public final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());


    public AbstractService() {}

    public RestResponse invokeRestRequest(RestRequest request) {
        RestResponse response = null;
        try {
            String result = HttpPostClient.requestToServer(
                    request.getRestCommands().get(0).getCommand(),
                    request.toString());
            response = new RestResponse(RestCommand.parse(result));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return response;
    }

    public int invokeRestRequestAsInt(RestRequest request) {
        int code;
        RestResponse response = invokeRestRequest(request);
        if (response != null) {
            code = Integer.valueOf(
                    response.getRestCommands().get(0).getParameter("errcode"));
        } else {
            code = Integer.valueOf(ErrorCode.PLATFORM_EXCEPTION);
        }
        return code;
    }
}
