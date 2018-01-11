package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2017/7/31.
 */
public interface ErrorCode {
    //数据库异常
    public static final String DATABASE_EXCEPTION = "9902";

    //请求参数异常
    public static final String REQUEST_PARAMETER_EXCEPTION = "9903";

    //session 失效
    public static final String SESSION_INVALID = "9904";
    //MQ消息推送失败
    public static final String RABBITMQ_EXCEPTION = "1101";

    //平台异常
    public static final String PLATFORM_EXCEPTION = "9901";

    public static final String ADMIN_USER_NOT_EXIST = "3100";//用户不存在
    public static final String USER_EXPIRE = "3101";//用户过期了请先激活！
    public static final String NOT_USER_MANAGE = "3102";//非管理者无法登陆
    public static final String ADD_USER_FAIL= "3103";//管理员添加失败
    public static final String LOGIN_NAME_EXIST= "3104";//此登录名已有
    public static final String PWD_ERROR= "4444";//密码错误
    public static final String NOT_LOGIN= "3105";//请先登录
    public static final String OVERTIME= "3106";//登陆超时
    public static final String NOT_DELETE_OWN= "3107";//不能删除自己
    public static final String WRONG_EMAIL= "3108";//登陆名不合法
    public static final String NOTQUERY_USER_MANAGE = "3109";//非管理者无法查看
    //登录失败，账号或密码错误
    public static final String LOGIN_FAILED = "1700";
    public static final String PROJECTNAME_EXIST = "3110";//此项目已有

    public static final String WEEKLY_EXIST = "4001";//本周周报已写

    public static final String CLUE_EXIST = "4002";//线索已写

    public static final String ALREADY_SIGN_UP = "4003";//已经报名


}
