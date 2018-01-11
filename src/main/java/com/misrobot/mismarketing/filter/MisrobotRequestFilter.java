package com.misrobot.mismarketing.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.misrobot.mismarketing.constants.PlatformPath;
import com.misrobot.mismarketing.pojo.mismarketing.dao.LogPlatformReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * REST接口过滤器，可以在此统一处理请求日志
 * <p>
 * Created by sushi on 16/9/23.
 */
@SuppressWarnings("unused")
public class MisrobotRequestFilter extends OncePerRequestFilter {
    private final ObjectMapper mapper = new ObjectMapper();

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    WebApplicationContext      web    = null;
//
//    @Override
//    protected void initFilterBean() throws ServletException {
//        try {
//            web = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        super.initFilterBean();
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "POST");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true"); //是否支持cookie跨域
        if (!"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        RequestWrapper requestWrapper = new RequestWrapper(httpServletRequest);

        ResponseWrapper responseWrapper = new ResponseWrapper(httpServletResponse);
        String body = requestWrapper.getBody();

        StringBuffer builder = new StringBuffer();

        String reqUrl = String.format("From %s %s %s", httpServletRequest.getRemoteAddr(),
                httpServletRequest.getMethod(), httpServletRequest.getRequestURL());
        String reqBody = String.format("Request Body: %s", body);
        log.info(reqUrl);
        log.info(reqBody);
        builder.append(reqUrl);
        builder.append("\r\n");
        builder.append(reqBody);
        LogPlatformReq req = new LogPlatformReq();
        req.setCommand(PlatformPath.LOG.getValue());

        //为了解决发送平台日志,不影响正常的业务,故发送请求日志和返回日志分别catch异常
        try {
            // PlatformProxyService proxyService =
            // (PlatformProxyService)web.getBean("platformProxyServiceImpl");

            req.setLogcontent(builder.toString());
            // proxyService.routeMessageToPlatform(
            // JsonUtil.getJSONString(req), PlatformPath.LOG.getValue());

//            HttpPostClient.requestToServer(PlatformPath.LOG.getValue(),
//                    JsonUtil.getJSONString(req));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        filterChain.doFilter(requestWrapper, responseWrapper);

        try {
            String respBody = responseWrapper.getBody();
            log.info(String.format("Response: %s", respBody));
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
//            outputStream.write(respBody.getBytes());
            outputStream.write(respBody.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            builder.setLength(0);
            builder.append(reqUrl);
            builder.append("\r\n");
            builder.append(respBody);
            req.setLogcontent(builder.toString());
//            HttpPostClient.requestToServer(PlatformPath.LOG.getValue(),
//                    JsonUtil.getJSONString(req));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        // JsonNode nodes = mapper.readTree(body);
        // for (JsonNode node : nodes) {
        // String command = node.get("command").asText();
        // String contextPath = httpServletRequest.getContextPath();
        // if (!contextPath.endsWith("//")) {
        // contextPath += "/";
        // }
        // command = contextPath + command;
        //
        // if (command == null || 0 !=
        // httpServletRequest.getRequestURI().compareTo(command)) {
        // //throw new ServletException("Argument [command] is no valid");
        // log.warn("Argument [command] is not valid.");
        // }
        // }

    }
}
