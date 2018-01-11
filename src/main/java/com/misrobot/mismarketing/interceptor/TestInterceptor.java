package com.misrobot.mismarketing.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GYQ on 2017/7/26.
 */
public class TestInterceptor extends HandlerInterceptorAdapter {
    //private static final Logger log = LoggerFactory.getLogger(TestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("preHandle");
//        MyRequestWrapper myRequestWrapper = new MyRequestWrapper((HttpServletRequest) request);
//        String body = myRequestWrapper.getBody();
//        request.setAttribute("json", body);

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//    	System.out.println("postHandle");
//    	Map<String, Object> model = modelAndView.getModel();
//        String encryptMsg = IOUtils.toString(request.getInputStream(), "utf-8");
//        String string = IOUtils.toString(request.getInputStream(), "utf-8");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//    	System.out.println("afterCompletion");
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//    	System.out.println("afterConcurrentHandlingStarted");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
