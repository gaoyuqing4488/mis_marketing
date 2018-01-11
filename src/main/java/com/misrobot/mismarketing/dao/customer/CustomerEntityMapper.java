package com.misrobot.mismarketing.dao.customer;

import com.misrobot.mismarketing.entity.CustomerEntity;
import com.misrobot.mismarketing.pojo.customerProject.ImporantCustmProject;
import com.misrobot.mismarketing.pojo.customerProject.ImportantCustomer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public interface CustomerEntityMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerEntity record);

    int insertSelective(CustomerEntity record);

    CustomerEntity selectByPrimaryKey(int id);

    CustomerEntity selectByCustomerID(@Param("customerid") int customerid, @Param("offset") int offset, @Param("pagesize") int pagesize);

    int getCustomerCountByPrimaryKey(int id);

    List<CustomerEntity> findAllRecords(@Param("offset") int offset, @Param("pagesize") int pagesize);

    int getAllCustomerCount();

    List<CustomerEntity> findAllRecordsByUserID(@Param("userid") int userid, @Param("offset") int offset, @Param("pagesize") int pagesize);

    int getCustomerCountByUserID(int userid);

    int updateByPrimaryKey(CustomerEntity record);

    void addProjectNumber(@Param("customerid") int customerid, @Param("countadded") int countadded);

    /*获取重要客户*/
    List<ImportantCustomer> getImportCustomers() throws SQLException;

    /*获取我的重要项目与客户*/
    List<ImporantCustmProject> getMyImportCustomers(Map<String, Long> map) throws SQLException;
}