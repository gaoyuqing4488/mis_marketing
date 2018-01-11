package com.misrobot.mismarketing.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * Created by gao on 2017/5/11.
 */
public class PasswordTool {
    public static String getSale(){
        return UUID.randomUUID().toString();
    }

    public static String getPwdEncode(String pwd,String sale){
        return DigestUtils.md5Hex(pwd+sale);
    }
}
