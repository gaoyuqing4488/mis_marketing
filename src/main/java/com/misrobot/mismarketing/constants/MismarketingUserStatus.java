package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2017/7/31.
 */
public enum MismarketingUserStatus {
    ENABLE(0, "可用"),
    DISABLE(1, "不可用"),
    WAITIING(2, "待激活");
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
    private MismarketingUserStatus(Integer value, String desc) {
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

