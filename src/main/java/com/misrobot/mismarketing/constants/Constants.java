package com.misrobot.mismarketing.constants;

import com.misrobot.mismarketing.util.AppUtil;

/**
 * Created by GYQ on 2017/7/25.
 */
public interface Constants {
    public static final String SUCCESS = "0";

    public static final String FAIL = "1";

    public static final String SMSTIMES = "3";

    /**
     * 未收到短信确认通知
     */
    public static final String CONFIRM_NOT = "0";

    /**
     * 收到短信确认通知
     */
    public static final String CONFIRM_YES = "1";

    public static final String FIRST_JSON = "1";

    /**
     * 返回码，成功为 0
     */
    public static final int ERRCODE_SUCCESS = 0;

    /**
     * 返回码，失败为 1
     */
    public static final int ERRCODE_FAIL = 0;

    /**
     * 参数错误返回码
     */
    public static final int ERRCODE_PARAMETER_ERROR = 1901;
}
