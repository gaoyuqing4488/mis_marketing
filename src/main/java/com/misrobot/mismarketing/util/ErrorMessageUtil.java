package com.misrobot.mismarketing.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * 功能描述： 错误信息工具类
 * @author 作者 liuqian@misrobot.com
 * @created 2016年10月1日 上午11:39:30
 * @version 1.0.0
 * @date 2016年10月1日 上午11:39:30
 */
public class ErrorMessageUtil {

    private static final Logger log = LoggerFactory.getLogger(ErrorMessageUtil.class);

    private static Properties pro = new Properties();

    static {
        InputStream appProperties = ErrorMessageUtil.class.getResourceAsStream("/errorcode.properties");
        try {
            pro.load(appProperties);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static  String getMessage(String key,String... strs) {
        String message;
        String msgContent = pro.getProperty(key);
        if (msgContent == null) {
            message = "Unknown Error Code";
        } else {
            message = MessageFormat.format(msgContent, strs);
        }

        return message;
    }
}

