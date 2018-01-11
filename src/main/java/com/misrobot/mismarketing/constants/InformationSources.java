package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2018/1/8.
 */
public enum InformationSources {
    EXHIBITION(0, "会展"),
    VISIT(1, "拜访"),
    TELEPHONE(2, "电话"),
    INTRODUCTION(3, "介绍");
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
    private InformationSources(Integer value, String desc) {
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
