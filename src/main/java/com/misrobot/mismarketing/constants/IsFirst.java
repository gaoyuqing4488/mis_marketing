package com.misrobot.mismarketing.constants;

public enum IsFirst {
    YES(0, "是"),
    NO(1, "非");

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
    private IsFirst(Integer value, String desc) {
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

