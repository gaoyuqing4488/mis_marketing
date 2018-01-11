package com.misrobot.mismarketing.dao.customer;

import com.misrobot.mismarketing.entity.CustomerEntity;
import com.misrobot.mismarketing.entity.CustomerStatisticsEntity;
import com.misrobot.mismarketing.pojo.customerStatistics.CustomerStatisticsInfo;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by CHJ on 2017/8/7.
 */
@Repository
public interface CustomerStatisticsEntityMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByCustomerId(Integer customerid);

    int insert(CustomerStatisticsEntity record);

    int insertSelective(CustomerStatisticsEntity record);

    List<CustomerStatisticsEntity> findByCustomerID(Integer customerid);

    List<CustomerStatisticsEntity> selectByPrimaryKey(Integer id);

    List<CustomerStatisticsEntity> findAllRecords();

    int updateByPrimaryKey(CustomerStatisticsEntity record);

    int updateByCustomerID(CustomerStatisticsEntity record);

    /*获取每年客户的投资额*/
    public List<CustomerStatisticsInfo> getCustomerStatisticsByYear() throws SQLException;

    /*获取我的客户投资额*/
    public List<CustomerStatisticsInfo> getMyCustomerStatisticsByYear(Map<String, Long> map) throws SQLException;
}
