package com.misrobot.mismarketing.dao.customer;

import com.misrobot.mismarketing.entity.CustomerDetailEntity;
import com.misrobot.mismarketing.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CHJ on 2017/8/2.
 */
@Repository
public interface CustomerDetailEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByCustomerId(Integer customerid);

    int insert(CustomerDetailEntity entity);

    int insertSelective(CustomerDetailEntity entity);

    CustomerDetailEntity selectByPrimaryKey(Integer id);

    List<CustomerDetailEntity> findAllRecords();

    List<CustomerDetailEntity> findAllRecordsByUserID(int userid);

    List<CustomerDetailEntity> findByCustomerID(int customerid);

    int updateByPrimaryKey(CustomerDetailEntity entity);

    int updateSubInfoContent(CustomerDetailEntity entity);
}
