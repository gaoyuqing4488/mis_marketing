package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2018/1/5.
 */
public enum CustomerRate {
    HEADMASTER(0, "校长"),
    DEAN(1, "院长"),
    DIRECTOR(2, "技能中心主任"),
    TEACHER(3, "普通教师"),
    OTHER(4, "其他");


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
    private CustomerRate(Integer value, String desc) {
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
