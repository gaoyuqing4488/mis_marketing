package com.misrobot.mismarketing.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.misrobot.mismarketing.constants.PlatformPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * HTTP请求工具类
 *
 * @author liuqian
 */
public class HttpPostClient {

    private static final Logger log = LoggerFactory.getLogger(HttpPostClient.class);

    /**
     * http post连接超时时间 单位毫秒 10秒
     */
    private static int httpConnectionTimeout = 60 * 1000;

    /**
     * http post 操作超时时间 单位 5秒
     */
    private static int httpOptTimeout = 5 * 1000;

    /**
     * http HEAD连接超时时间 单位毫秒 60秒
     */
    private static int httpHeadConnectionTimeout = 60 * 1000;

    /**
     * http HEAD 操作超时时间 单位 5秒
     */
    private static int httpHeadOptTimeout = 5 * 1000;

    /**
     * 功能描述: 发送请求到外围系统<br>
     * 〈包括：连接错误处理，异常处理〉
     *
     * @param path 请求的path
     * @return
     * @throws IOException
     * @author liuqian
     * @jsonData 需要发送的json数据
     */
    public static String requestToServer(String path, String jsonData) throws IOException {
        return requestToServer2(AppUtil.getMessage("platformURL") + path, jsonData);
    }

    public static String requestToServer2(String path, String jsonData) throws IOException {
        HttpURLConnection httpURLConnection = null;
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;
        InputStream u = null;
        try {
            URL url = new URL(path);
            // proxy
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("contentType", "utf-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(httpConnectionTimeout);
            httpURLConnection.setReadTimeout(httpOptTimeout);
            printLog(path, jsonData);
            // 发送数据
            PrintWriter out = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
            out.print(jsonData);
            out.flush();
            out.close();
            // 接收
            int rec = 0;
            rec = httpURLConnection.getResponseCode();
            if (httpURLConnection.getResponseCode() == httpURLConnection.HTTP_OK) {
                u = httpURLConnection.getInputStream();
                in = new BufferedReader(new InputStreamReader(u, "UTF-8"));
                String line = "";
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\r\n");
                }
            } else {
                log.error("返回码:" + rec);
                return null;
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (u != null) {
                u.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        String result = sb.toString();
        printLog(path, result);
        return result;
    }

    /**
     * 功能描述：
     * 输入参数：<按照参数定义顺序>
     *
     * @param type 请求
     *             返回值:  类型 <说明>
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    private static void printLog(String path, String content) {
        if (!PlatformPath.LOG.getValue().equals(path)) {
            log.debug(content);
        }
    }

    /**
     * 重要:切记乱用;
     * 因为应用层需要拿到HttpURLConnection和InputStream,所以InputStream流关闭和HttpURLConnection关闭放到应用层;
     * 功能描述: HEAD业务层文件流 目前主要用于H5模块的版本校验<br>
     * 〈包括：连接错误处理，异常处理〉
     *
     * @param path 请求的path
     * @return
     * @throws IOException
     * @author liuqian
     */
    public static HttpURLConnection getServiceInfo(String path) {
        HttpURLConnection httpURLConnection = null;
//        InputStream u = null;
        try {
            URL url = new URL(AppUtil.getMessage("serviceURL") + path);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("HEAD");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(httpHeadConnectionTimeout);
            httpURLConnection.setReadTimeout(httpHeadOptTimeout);
//          u = httpURLConnection.getInputStream();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
//            if (httpURLConnection != null) {
//                httpURLConnection.disconnect();
//            }
        }
        return httpURLConnection;
    }

}

