package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2018/1/5.
 */
public enum Level {
    PROVINCE(0, "省属"),
    CITY(1, "市属"),
    PART(2, "部属"),
    COOPERATE(3, "省部共建"),
    PRIVATE(4, "私立"),
    THREECLASSA(5, "三级甲等"),
    THREECLASSB(6, "三级乙等"),
    SECOND(7, "二甲"),
    OTHER(8, "其他");


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
    private Level(Integer value, String desc) {
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
