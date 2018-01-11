package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2017/7/25.
 */
public enum PlatformPath {
    QUERY_QUESTION12("question/queryquestioninfo", "查询考题"),
    //查询考题
    QUERY_QUESTION("question/queryquestioninfo", "查询考题"),

    //    //查询考生状态信息
    QUERY_EXAMINEESTATUS("schedule/queryexamineestatus", "查询考生状态信息"),

//    //下一位考生
//    NEXT_EXAMINEE("/schedule/nextexaminee","下一位考生"),

    //查询房间状态
    QUERY_ROOMSTATUS("examstation/queryroomstatus", "查询房间状态"),

    //    //查询考生的基本信息
    QUERY_USERINFO("usr/queryuserinfo", "查询用户的基本信息"),

//    //查询考题
//    QUERY_QUESTIONINFO("/question/queryquestioninfo","查询考题"),

//    //查询评分表
//    QUERY_GRADEITEMLIST("/grade/getgradeitemlist","查询评分表"),

    //发送短消息
    SENDSM("sendsm", "发送短消息"),

    LOGIN("usr/login", "平台登录"),//平台登录接口

    //发短信验证码
    SENDVERIFICATIONCODE("usr/resetpasswordrequest", "发短信验证码"),

    //校验验证码
    VALIDATEVERIFICATIONCODE("usr/verifycodeisvalid", "校验验证码"),

    //查询考生考试确认状态
    QUERY_CONFIRM_STATUS("schedule/queryconfirmstatus", "查询考生考试确认状态"),

    //设置考生考试确认状态
    SET_CONFIRM_STATUS("schedule/setconfirmstatus", "设置考生考试确认状态"),

    //提交成绩
    SUBMIT_SCORE("score/submitscore", "提交成绩"),

    //查询成绩
    QUERY_SCORE("score/queryscore", "查询成绩"),

    //查询成绩,武大技能大赛
    COMPARE_SCORE("score/comparescore", "查询比对成绩"),

    //成绩核查
    QUERY_SCORELIST("score/queryscorelist", "成绩核查"),

    //设置房间状态
    SET_ROOMSTATUS("examstation/setroomstatus", "设置房间状态"),

    // 考生检录
    EXAMINNE_CHECKIN("schedule/examineecheckin", "考生检录"),

    // 721考生检录
    EXAMINNE_CHECKIN721("schedule/examinee3rdcheckin", "考生检录"),

    // 考生确认
    EXAMINNE_CHECKIN_CONFIRM("schedule/confirmexamineecheckin", "考生检录确认"),

    // 查询考生最近的考试信息
    QUERY_NEXTEXAM_FOR_EXAMINEE("schedule/querynextexamofexaminee", "查询考生最近的考试"),

    //平台日志
    LOG("log", "平台日志"),

    // 查询设备信息
    QUERY_DEVICE_INFO("examstation/querydeviceinfo", "查询设备信息"),

    UPDATE_EXAM_GROUP_IDENTIFY_CODE("examstation/updateexamgroupidentifycode", "更新考组验证码"),

    QUERY_EXAM_GROUP_IDENTIFY_CODE("examstation/queryexamgroupidentifycode", "查询考组验证码"),

    BIND_DEVICE_AND_ROOM("examstation/binddeviceandroom", "绑定设备和房间"),

    UNBIND_DEVICE_AND_ROOM("examstation/unbinddeviceandroom", "解绑设备和房间"),

    // 查询考官信息
    QUERY_EXAMINER_INFO("examstation/queryexaminerinfo", "查询考官信息"),

    QUERY_EXAM_INFO("examstation/queryexaminfo", "查询考试信息"),

    // 查询考试报告
    QUERY_TEST_REPORT("osce/report/testreport", "查询考生考试报告"),

    QUERY_EXAMINER_REPORT("osce/report/examinerreport", "查询考官考试报告"),

    QUERY_MANAGER_REPORT("osce/report/managerreport", "查询管理者考试报告"),;

    /**
     * field值
     */
    private String value;

    /**
     * 描述
     */
    private String desc;

    /**
     * 构造方法
     *
     * @param value field值
     * @param desc  描述
     */
    private PlatformPath(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    public static void main(String[] args) {
        System.out.println("test commit1");
    }
}
