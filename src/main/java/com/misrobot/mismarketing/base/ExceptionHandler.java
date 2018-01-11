package com.misrobot.mismarketing.base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by GYQ on 2017/7/26.
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

        String ticketNo = String.valueOf(System.currentTimeMillis());

        log.error("Exception occured. [TICKET-" + ticketNo + "], ", ex);


        ModelAndView rlt = new ModelAndView("exception");

        rlt.getModel().put("tickNo", ticketNo);

        return rlt;
    }

}
