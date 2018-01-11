package com.misrobot.mismarketing.util;

import com.misrobot.mismarketing.constants.Constants;
import com.misrobot.mismarketing.exceptions.RestException;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by CHJ on 2017/10/17.
 */
public class TokenUtil {
    public static final String TOKEN_TAG = "systemUserToken";

    public static boolean checkLoginInfo(Long userid, HttpSession session) {
        if (AppUtil.isDebugMode()) return true;
        String ua = SpringWebTool.getRequest().getHeader("User-Agent");
        if (AgentUtil.checkAgentIsMobile(ua)) {
            return true;
        }
        String token = getTokenInfo(userid, session);
        return token != null;
    }

    private static String getTokenInfo(Long userid, HttpSession session) {
        Object attribute = session.getAttribute(TOKEN_TAG + userid);
        session.setMaxInactiveInterval(36000);
        if (attribute != null) {
            return (String) attribute;
        }
        return null;
    }

    public static String getUserToken(Long userid, HttpSession session) {
        String token = "uc." + UUID.randomUUID().toString();
//        SpringWebTool.getRequest().getSession().setAttribute(TOKEN_TAG+userid,token);
//        SpringWebTool.getRequest().getSession().setMaxInactiveInterval(36000);
        session.setAttribute(TOKEN_TAG + userid, token);
        System.out.println("+++++++++++++++++++++++++++++++++++" + session.toString());
        session.setMaxInactiveInterval(36000);
        return token;

    }

    public static String getToken(Long userid) {
        Object attribute = SpringWebTool.getRequest().getSession().getAttribute(TOKEN_TAG + userid);
        SpringWebTool.getSession().setMaxInactiveInterval(36000);

        if (attribute != null) {
            return (String) attribute;
        }
        return null;
    }

    public static void removeToken(Long userid) throws RestException {
        Object attribute = SpringWebTool.getSession().getAttribute(TOKEN_TAG + userid);
        SpringWebTool.getSession().removeAttribute(TOKEN_TAG + userid);
    }

}
