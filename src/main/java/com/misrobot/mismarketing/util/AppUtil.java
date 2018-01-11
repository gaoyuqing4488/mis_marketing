package com.misrobot.mismarketing.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能描述： app.properties 属性工具类
 *
 * @author 作者 liuqian@misrobot.com
 * @version 1.0.0
 * @created 2016年10月10日 下午8:31:08
 * @date 2016年10月10日 下午8:31:08
 */
public class AppUtil {

    private static final Logger log = LoggerFactory.getLogger(AppUtil.class);

    private static Properties pro = new Properties();

    static {
        InputStream appProperties = AppUtil.class.getResourceAsStream("/app.properties");
        try {
            pro.load(appProperties);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static String getMessage(String key) {

        String msgContent = pro.getProperty(key);

        return msgContent;
    }

    public static boolean isDebugMode() {
        String mode = AppUtil.getMessage("is_debug_mode");
        return "1".equals(mode);
    }

}

