package com.misrobot.mismarketing.pojo.weekly;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.JsonObject;
import com.misrobot.mismarketing.pojo.BasePojo;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 周报基本信息
 * Created by GYQ on 2017/8/2.
 */
public class Weekly extends BasePojo {

    private Integer id;

    private String name;

    private Long user_id;

    private Date create_time;

    private Date update_time;

    private BigDecimal sales;

    @NumberFormat(pattern = "0.00")
    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

}
