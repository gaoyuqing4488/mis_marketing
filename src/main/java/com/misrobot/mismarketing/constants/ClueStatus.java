package com.misrobot.mismarketing.constants;

/**
 * Created by GYQ on 2018/1/5.
 */
public enum ClueStatus {
    FOLLOWING(0, "跟进中"),
    CLOSEREACTION(1, "关闭转机会项目"),
    NOREACTIONCLOSE(2, "非机会关闭"),
    CLOSECLUE(3, "重复线索关闭");



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
    private ClueStatus(Integer value, String desc) {
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
