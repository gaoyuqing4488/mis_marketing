package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2017/7/31.
 */
public enum MismarketingUserType {
    MANAGE(0, "管理员"),
    COMMON(1, "普通用户");

    /**
     * field值
     */
    private Integer value;

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
    private MismarketingUserType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
}

