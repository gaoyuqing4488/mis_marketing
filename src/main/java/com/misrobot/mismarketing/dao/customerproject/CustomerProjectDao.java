package com.misrobot.mismarketing.dao.customerproject;

import com.misrobot.mismarketing.entity.CustomerProjectEntity;

import java.util.List;

/**
 * Created by GYQ on 2017/8/8.
 */
public interface CustomerProjectDao {

    int insert(CustomerProjectEntity record);

    int insertSelective(CustomerProjectEntity record);

    CustomerProjectEntity selectByPrimaryKey(Integer id);

    int deleteByCustomerId(int customerid);

    int deleteByProjectId(int projectid);

    int deleteByPrimaryKey(Integer id);

    List<CustomerProjectEntity> findAllRecords();

    List<CustomerProjectEntity> findAllRecordsByProjectId(int projectid);

    List<CustomerProjectEntity> findAllRecordsByCustomerId(int customerid);

    int updateByPrimaryKey(CustomerProjectEntity record);
}
