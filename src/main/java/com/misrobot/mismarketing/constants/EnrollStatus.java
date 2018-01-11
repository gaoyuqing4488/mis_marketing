package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2018/1/9.
 */
public enum EnrollStatus {
    HASENROLL(0, "已报名"),
    NOTENROLL(1, "未报名");


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
    private EnrollStatus(Integer value, String desc) {
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
