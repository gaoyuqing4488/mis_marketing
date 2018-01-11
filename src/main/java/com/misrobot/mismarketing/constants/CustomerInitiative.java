package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2018/1/8.
 */
public enum CustomerInitiative {
    CUSTOMER(0, "客户主动"),
    OUR(1, "我司主动");



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
    private CustomerInitiative(Integer value, String desc) {
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
