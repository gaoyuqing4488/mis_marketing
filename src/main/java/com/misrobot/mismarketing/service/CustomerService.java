package com.misrobot.mismarketing.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.misrobot.mismarketing.dao.customer.CustomerDetailEntityMapper;
import com.misrobot.mismarketing.dao.customer.CustomerEntityMapper;
import com.misrobot.mismarketing.dao.customer.CustomerStatisticsEntityMapper;
import com.misrobot.mismarketing.dao.project.ProjectEntityMapper;
import com.misrobot.mismarketing.entity.CustomerDetailEntity;
import com.misrobot.mismarketing.entity.CustomerEntity;
import com.misrobot.mismarketing.entity.CustomerStatisticsEntity;
import com.misrobot.mismarketing.util.DateUtil;
import com.misrobot.mismarketing.util.JsonUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by CHJ on 2017/7/28.
 */
@Service
public class CustomerService {

    @Autowired
    CustomerEntityMapper mCustomerEntityMapper;

    @Autowired
    CustomerDetailEntityMapper mCustomerDetailEntityMapper;

    @Autowired
    private CustomerStatisticsEntityMapper mCustomerStatisticsEntityMapper;

    @Autowired
    ProjectEntityMapper mProjectEntityMapper;

    public List<CustomerEntity> findAllMainItems(int offset, int pagesize) {
        return mCustomerEntityMapper.findAllRecords(offset, pagesize);
    }

    public int getAllCustomerCount() {
        return mCustomerEntityMapper.getAllCustomerCount();
    }

    public List<CustomerDetailEntity> findAllSubItems() {
        return mCustomerDetailEntityMapper.findAllRecords();
    }

    public List<CustomerEntity> findMainItemsByUserID(int userid, int offset, int pagesize) {
        return mCustomerEntityMapper.findAllRecordsByUserID(userid, offset, pagesize);
    }

    public int getCustomerCountByUserID(int userid) {
        return mCustomerEntityMapper.getCustomerCountByUserID(userid);
    }

    public List<CustomerDetailEntity> findSubItemsByUserID(int userid) {
        return mCustomerDetailEntityMapper.findAllRecordsByUserID(userid);
    }

    public CustomerEntity findMainItemByCustomerID(Integer customerid, Integer offset, Integer pagesize) {
        return mCustomerEntityMapper.selectByCustomerID(customerid, offset, pagesize);
    }

    public int getCustomerCountByPrimaryKey(Integer id) {
        return mCustomerEntityMapper.getCustomerCountByPrimaryKey(id);
    }

    public List<CustomerDetailEntity> findSubItemByCustomerID(Integer customerid) {
        return mCustomerDetailEntityMapper.findByCustomerID(customerid);
    }

    private static SqlSessionFactory sqlSessionFactory;

    @Transactional
    public int insertNewCustomerInfo(CustomerEntity customerEntity, JsonNode customer_detail_info) {
        int count = mCustomerEntityMapper.insertSelective(customerEntity);
        int customerId = customerEntity.getId();
        Iterator<JsonNode> iterator = customer_detail_info.elements();
        while (iterator.hasNext()) {
            JsonNode jsonNode = iterator.next();
            CustomerDetailEntity customerDetailEntity = new CustomerDetailEntity();
            customerDetailEntity.setCustomerid(customerId);
            customerDetailEntity.setCustomerownerid(customerEntity.getCustomerownerid());
            customerDetailEntity.setInfotype(jsonNode.get("infotype").asInt(0));
            customerDetailEntity.setSubinfotype(jsonNode.get("subinfotype").asInt(0));
            customerDetailEntity.setSubinfocontent(JsonUtil.getJSONString(jsonNode.get("subinfocontent")));
            mCustomerDetailEntityMapper.insertSelective(customerDetailEntity);
        }
        Date date = Timestamp.from(Instant.now());
        CustomerStatisticsEntity customerStatisticsEntity_1 = new CustomerStatisticsEntity();
        CustomerStatisticsEntity customerStatisticsEntity_2 = new CustomerStatisticsEntity();
        CustomerStatisticsEntity customerStatisticsEntity_3 = new CustomerStatisticsEntity();

        customerStatisticsEntity_1.setCustomerid(customerId);
        customerStatisticsEntity_1.setMoney(customerEntity.getBudgetsale_1());
        customerStatisticsEntity_1.setCreatetime(Timestamp.from(Instant.now()));
        customerStatisticsEntity_1.setMonth_(Integer.valueOf(DateUtil.getInstance().getMonth(date)));
        customerStatisticsEntity_1.setYear_(Integer.valueOf(DateUtil.getInstance().getYear(date)));
        customerStatisticsEntity_1.setUserid(customerEntity.getCustomerownerid());

        customerStatisticsEntity_2.setCustomerid(customerId);
        customerStatisticsEntity_2.setMoney(customerEntity.getBudgetsale_2());
        customerStatisticsEntity_2.setCreatetime(Timestamp.from(Instant.now()));
        customerStatisticsEntity_2.setMonth_(Integer.valueOf(DateUtil.getInstance().getMonth(date)));
        customerStatisticsEntity_2.setYear_(Integer.valueOf(DateUtil.getInstance().getYear(date)) + 1);
        customerStatisticsEntity_2.setUserid(customerEntity.getCustomerownerid());

        customerStatisticsEntity_3.setCustomerid(customerId);
        customerStatisticsEntity_3.setMoney(customerEntity.getBudgetsale_3());
        customerStatisticsEntity_3.setCreatetime(Timestamp.from(Instant.now()));
        customerStatisticsEntity_3.setMonth_(Integer.valueOf(DateUtil.getInstance().getMonth(date)));
        customerStatisticsEntity_3.setYear_(Integer.valueOf(DateUtil.getInstance().getYear(date)) + 2);
        customerStatisticsEntity_3.setUserid(customerEntity.getCustomerownerid());

        mCustomerStatisticsEntityMapper.insert(customerStatisticsEntity_1);
        mCustomerStatisticsEntityMapper.insert(customerStatisticsEntity_2);
        mCustomerStatisticsEntityMapper.insert(customerStatisticsEntity_3);

        return customerId;
        //        SqlSession sqlSession = MyBatisUtil.getSqlSession(); // 打开会话，事务开始
        //        sqlSession.commit();
        //        sqlSession.rollback();
        //        sqlSession.close();
    }

    @Transactional
    public void updateCustomerInfo(CustomerEntity customerEntity, JsonNode customer_detail_info) {
        mCustomerEntityMapper.updateByPrimaryKey(customerEntity);
        int customerId = customerEntity.getId();
        Iterator<JsonNode> iterator = customer_detail_info.elements();
        while (iterator.hasNext()) {
            JsonNode jsonNode = iterator.next();
            CustomerDetailEntity customerDetailEntity = new CustomerDetailEntity();
            customerDetailEntity.setCustomerid(customerId);
            customerDetailEntity.setCustomerownerid(customerEntity.getCustomerownerid());
            customerDetailEntity.setInfotype(jsonNode.get("infotype").asInt(0));
            customerDetailEntity.setSubinfotype(jsonNode.get("subinfotype").asInt(0));
            customerDetailEntity.setSubinfocontent(JsonUtil.getJSONString(jsonNode.get("subinfocontent")));
            mCustomerDetailEntityMapper.updateSubInfoContent(customerDetailEntity);
        }
        Date date = DateUtil.getCurrentDateTime();
        CustomerStatisticsEntity customerStatisticsEntity_1 = new CustomerStatisticsEntity();
        CustomerStatisticsEntity customerStatisticsEntity_2 = new CustomerStatisticsEntity();
        CustomerStatisticsEntity customerStatisticsEntity_3 = new CustomerStatisticsEntity();

        customerStatisticsEntity_1.setCustomerid(customerId);
        customerStatisticsEntity_1.setMoney(customerEntity.getBudgetsale_1());
        customerStatisticsEntity_1.setCreatetime(Timestamp.from(Instant.now()));
        customerStatisticsEntity_1.setMonth_(Integer.valueOf(DateUtil.getInstance().getMonth(date)));
        customerStatisticsEntity_1.setYear_(Integer.valueOf(DateUtil.getInstance().getYear(date)));
        customerStatisticsEntity_1.setUserid(customerEntity.getCustomerownerid());

        customerStatisticsEntity_2.setCustomerid(customerId);
        customerStatisticsEntity_2.setMoney(customerEntity.getBudgetsale_2());
        customerStatisticsEntity_2.setCreatetime(Timestamp.from(Instant.now()));
        customerStatisticsEntity_2.setMonth_(Integer.valueOf(DateUtil.getInstance().getMonth(date)));
        customerStatisticsEntity_2.setYear_(Integer.valueOf(DateUtil.getInstance().getYear(date)) + 1);
        customerStatisticsEntity_2.setUserid(customerEntity.getCustomerownerid());

        customerStatisticsEntity_3.setCustomerid(customerId);
        customerStatisticsEntity_3.setMoney(customerEntity.getBudgetsale_3());
        customerStatisticsEntity_3.setCreatetime(Timestamp.from(Instant.now()));
        customerStatisticsEntity_3.setMonth_(Integer.valueOf(DateUtil.getInstance().getMonth(date)));
        customerStatisticsEntity_3.setYear_(Integer.valueOf(DateUtil.getInstance().getYear(date)) + 2);
        customerStatisticsEntity_3.setUserid(customerEntity.getCustomerownerid());

        mCustomerStatisticsEntityMapper.updateByCustomerID(customerStatisticsEntity_1);
        mCustomerStatisticsEntityMapper.updateByCustomerID(customerStatisticsEntity_2);
        mCustomerStatisticsEntityMapper.updateByCustomerID(customerStatisticsEntity_3);
    }

    @Transactional
    public void deleteCustomerInfo(int customerid) {
        mCustomerEntityMapper.deleteByPrimaryKey(customerid);
        mCustomerDetailEntityMapper.deleteByCustomerId(customerid);
        mCustomerStatisticsEntityMapper.deleteByCustomerId(customerid);
        mProjectEntityMapper.deleteByCustomerID(customerid);
    }

}
