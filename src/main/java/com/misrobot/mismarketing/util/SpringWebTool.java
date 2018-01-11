package com.misrobot.mismarketing.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SpringMVC，web相关的工具类
 */
public class SpringWebTool {

    public static HttpSession getSession(){
//        Executors.newCachedThreadPool()
        HttpServletRequest request = getRequest();
        //得到session
        return request.getSession();

    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //得到request

        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    public static ServletContext getServletContext(){
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }

    public static String getRealPath(String path){
        return getServletContext().getRealPath(path);
    }

    public static String getWebRootUrl(){
        HttpServletRequest request = getRequest();

        String host = request.getScheme()+
                "://"+request.getServerName()+
                ":"+request.getServerPort();
        return host+"/"+request.getContextPath()+"/";
    }

    public static <T> T getBean(Class<T> tClass){
        return ContextLoader.getCurrentWebApplicationContext().getBean(tClass);
    }

    public static <T> T getBean(String name,Class<T> tClass){
        return ContextLoader.getCurrentWebApplicationContext().getBean(name,tClass);
    }

    public static boolean isAjax(){

        String requestType = getRequest().getHeader("X-Requested-With");
        return !(requestType == null || "".equals(requestType));
    }

    public static void redirectUrl(String url){
        try {
            getResponse().sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

