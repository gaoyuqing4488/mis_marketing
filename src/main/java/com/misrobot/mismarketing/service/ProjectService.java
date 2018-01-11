package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.dao.customer.CustomerEntityMapper;
import com.misrobot.mismarketing.dao.customerproject.CustomerProjectDao;
import com.misrobot.mismarketing.dao.project.ProjectEntityMapper;
import com.misrobot.mismarketing.dao.project.ProjectHistoryEntityMapper;
import com.misrobot.mismarketing.dao.project.ProjectStatisticsEntityMapper;
import com.misrobot.mismarketing.entity.*;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.project.ProjectByWeekly;
import com.misrobot.mismarketing.util.DateUtil;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

/**
 * Created by CHJ on 2017/8/1.
 */
@Service
public class ProjectService {
    private final String HISTORY_TITLE = "Title";
    private final String HISTORY_CONTENT = "Content";

    @Autowired
    ProjectEntityMapper mProjectEntityMapper;

    @Autowired
    private ProjectStatisticsEntityMapper mProjectStatisticsEntityMapper;

    @Autowired
    private ProjectHistoryEntityMapper mProjectHistoryEntityMapper;

    @Autowired
    private CustomerProjectDao mCustomerProjectDao;

    @Autowired
    private CustomerEntityMapper mCustomerEntityMapper;

    public int insertSelective(ProjectEntity entity) {
        return mProjectEntityMapper.insertSelective(entity);
    }

    public List<ProjectEntity> selectByPrimaryKey(Integer id) {
        return mProjectEntityMapper.selectByPrimaryKey(id);
    }

    public int getProjectsCountByPrimaryKey(Integer id) {
        return mProjectEntityMapper.getProjectsCountByPrimaryKey(id);
    }

    public List<ProjectEntity> findAllProjects(int offset, int pagesize) {
        return mProjectEntityMapper.findAllProjects(offset, pagesize);
    }

    public int getAllProjectsCount() {
        return mProjectEntityMapper.getAllProjectsCount();
    }

    public List<ProjectEntity> findAllProjectsByUserID(int userid, int offset, int pagesize) {
        return mProjectEntityMapper.findAllProjectsByUserID(userid, offset, pagesize);
    }

    public int getProjectsCountByUserID(int userid) {
        return mProjectEntityMapper.getProjectsCountByUserID(userid);
    }

    public List<ProjectEntity> findAllProjectsByCustomerID(int customerid, int offset, int pagesize) {
        return mProjectEntityMapper.findAllProjectsByCustomerID(customerid, offset, pagesize);
    }

    public List<ProjectEntity> findByProjectNumber(String projectnumber) {
        return mProjectEntityMapper.findByProjectNumber(projectnumber);
    }

    @Transactional
    public int createNewProject(ProjectEntity entity) {
        int count = mProjectEntityMapper.insert(entity);
        //
        ProjectStatisticsEntity projectStatisticsEntity = new ProjectStatisticsEntity();
        Date billSignPlanDate = entity.getBillsignplandate();
        BigDecimal money;
        int billSignPlanYear = Integer.valueOf(DateUtil.getInstance().getYear(billSignPlanDate));
        int createYear = Integer.valueOf(DateUtil.getInstance().getYear(entity.getCreatetime()));
        int dif = billSignPlanYear - createYear;
        switch (dif) {
            case 0:
                money = entity.getCurrentbudgetmoney();
                break;
            case 1:
                money = entity.getBudgetmoney_1();
                break;
            case 2:
                money = entity.getBudgetmoney_2();
                break;
            default:
                money = entity.getCurrentbudgetmoney();
                break;
        }
        projectStatisticsEntity.setCreatetime(Timestamp.from(Instant.now()));
        projectStatisticsEntity.setUserid(entity.getProjectownerid());
        projectStatisticsEntity.setMoney(money);
        projectStatisticsEntity.setYear_(Integer.valueOf(DateUtil.getInstance().getYear(billSignPlanDate)));
        projectStatisticsEntity.setMonth_(Integer.valueOf(DateUtil.getInstance().getMonth(billSignPlanDate)));
        projectStatisticsEntity.setProjectid(entity.getId());
        projectStatisticsEntity.setStatus(entity.getStep());
        mProjectStatisticsEntityMapper.insert(projectStatisticsEntity);

        CustomerProjectEntity customerProjectEntity = new CustomerProjectEntity();
        customerProjectEntity.setCustomerid(entity.getProjectcustomerid());
        customerProjectEntity.setProjectid(entity.getId());
        mCustomerProjectDao.insert(customerProjectEntity);

        mCustomerEntityMapper.addProjectNumber(entity.getProjectcustomerid(), 1);

        return count;
    }

    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        Integer customerid = mProjectEntityMapper.getCustomerIDByPrimaryKey(id);
        int count = mProjectEntityMapper.deleteByPrimaryKey(id);
        mProjectStatisticsEntityMapper.deleteByProjectID(id);
        mCustomerProjectDao.deleteByProjectId(id);
        mProjectHistoryEntityMapper.deleteByProjectID(id);
        mCustomerEntityMapper.addProjectNumber(customerid, -1);
        return count;
    }

    @Transactional
    public int updateByPrimaryKey(@Nullable Integer beginbudgetyear, @Nullable ProjectEntity entity) {
        List<ProjectEntity> projectEntities = selectByPrimaryKey(entity.getId());
        ProjectEntity projectEntity = projectEntities.get(0);
        Map differ = getDifference(projectEntity, entity);
        int count = mProjectEntityMapper.updateByPrimaryKey(entity);
        ProjectStatisticsEntity projectStatisticsEntity = new ProjectStatisticsEntity();
        Date billSignPlanDate = entity.getBillsignplandate();
        int billSignPlanYear = Integer.valueOf(DateUtil.getInstance().getYear(billSignPlanDate));
        //BigDecimal money = entity.getCurrentbudgetmoney();
        BigDecimal money;
        int dif = billSignPlanYear - beginbudgetyear;
        switch (dif) {
            case 0:
                money = entity.getCurrentbudgetmoney();
                break;
            case 1:
                money = entity.getBudgetmoney_1();
                break;
            case 2:
                money = entity.getBudgetmoney_2();
                break;
            default:
                money = entity.getCurrentbudgetmoney();
                break;
        }
        projectStatisticsEntity.setUserid(entity.getProjectownerid());
        projectStatisticsEntity.setMoney(money);
        entity.getBudgetmoney_2();
        projectStatisticsEntity.setYear_(Integer.valueOf(DateUtil.getInstance().getYear(billSignPlanDate)));
        projectStatisticsEntity.setMonth_(Integer.valueOf(DateUtil.getInstance().getMonth(billSignPlanDate)));
        projectStatisticsEntity.setProjectid(entity.getId());
        projectStatisticsEntity.setStatus(entity.getStep());
        mProjectStatisticsEntityMapper.updateByProjectID(projectStatisticsEntity);
        //Add to history...
        if (!differ.isEmpty()) {
            ProjectHistoryEntity projectHistoryEntity = new ProjectHistoryEntity();
            projectHistoryEntity.setProjectid(entity.getId());
            projectHistoryEntity.setModifydate(Timestamp.from(Instant.now()));
            projectHistoryEntity.setTitle(String.valueOf(differ.get(HISTORY_TITLE)));
            projectHistoryEntity.setModifycontent(String.valueOf(differ.get(HISTORY_CONTENT)));
            try {
                mProjectHistoryEntityMapper.insertSelective(projectHistoryEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public List<ProjectHistoryEntity> queryAllHistoryRecords() {
        return mProjectHistoryEntityMapper.queryAllRecords();
    }

    public List<ProjectHistoryEntity> queryAllRecordsByProjectID(int projectid) {
        return mProjectHistoryEntityMapper.queryAllRecordsByProjectID(projectid);
    }

    public Map getDifference(@Nullable ProjectEntity oldObject, @Nullable ProjectEntity newObject) {
        Map map = new HashMap();
        StringBuilder titleBuilder = new StringBuilder();
        StringBuilder contentBuilder = new StringBuilder();

        if (!oldObject.getProjectname().equals(newObject.getProjectname())) {
            titleBuilder.append("项目名称");
            contentBuilder.append(oldObject.getProjectname()).append(" → ").append(newObject.getProjectname());
        }
        if (oldObject.getRate() != newObject.getRate()) {
            titleBuilder.append("&项目级别");
            contentBuilder.append("&").append(oldObject.getRate()).append(" → ").append(newObject.getRate());
        }
        if (oldObject.getStep() != newObject.getStep()) {
            titleBuilder.append("&当前项目阶段");
            contentBuilder.append("&").append(oldObject.getStep()).append(" → ").append(newObject.getStep());
        }
        if (!oldObject.getInfoprogress().equals(newObject.getInfoprogress())) {
            titleBuilder.append("&基本信息和进展");
            contentBuilder.append("&").append(oldObject.getInfoprogress()).append(" → ").append(newObject.getInfoprogress());
        }
        if (!oldObject.getDifficultyhelp().equals(newObject.getDifficultyhelp())) {
            titleBuilder.append("&困难与求助");
            contentBuilder.append("&").append(oldObject.getDifficultyhelp()).append(" → ").append(newObject.getDifficultyhelp());
        }
        if (!oldObject.getCurrentbudgetmoney().equals(newObject.getCurrentbudgetmoney())) {
            titleBuilder.append("&当年预算销售额");
            contentBuilder.append("&").append(oldObject.getCurrentbudgetmoney()).append(" → ").append(newObject.getCurrentbudgetmoney());
        }
        if (!oldObject.getCurrentactualmoney().equals(newObject.getCurrentactualmoney())) {
            titleBuilder.append("&当年实际销售额");
            contentBuilder.append("&").append(oldObject.getCurrentactualmoney()).append(" → ").append(newObject.getCurrentactualmoney());
        }
        if (null != oldObject.getProjectdate() && null != newObject.getProjectdate() &&
                !oldObject.getProjectdate().equals(newObject.getProjectdate())) {
            titleBuilder.append("&立项日期");
            contentBuilder.append("&").append(getDate(oldObject.getProjectdate())).append(" → ").append(getDate(newObject.getProjectdate()));
        }
        if (null != oldObject.getCapitaldate() && null != newObject.getCapitaldate() &&
                !oldObject.getCapitaldate().equals(newObject.getCapitaldate())) {
            titleBuilder.append("&资金到位日期");
            contentBuilder.append("&").append(getDate(oldObject.getCapitaldate())).append(" → ").append(getDate(newObject.getCapitaldate()));
        }
        if (null != oldObject.getCapitalusingdate() && null != newObject.getCapitalusingdate() &&
                !oldObject.getCapitalusingdate().equals(newObject.getCapitalusingdate())) {
            titleBuilder.append("&资金使用日期");
            contentBuilder.append("&").append(getDate(oldObject.getCapitalusingdate())).append(" → ").append(getDate(newObject.getCapitalusingdate()));
        }
        if (null != oldObject.getBidingdocumentdate() && null != newObject.getBidingdocumentdate() &&
                !oldObject.getBidingdocumentdate().equals(newObject.getBidingdocumentdate())) {
            titleBuilder.append("&发标日期");
            contentBuilder.append("&").append(getDate(oldObject.getBidingdocumentdate())).append(" → ").append(getDate(newObject.getBidingdocumentdate()));
        }
        if (null != oldObject.getBillsignplandate() && null != newObject.getBillsignplandate() &&
                !oldObject.getBillsignplandate().equals(newObject.getBillsignplandate())) {
            titleBuilder.append("&预计签单日期");
            contentBuilder.append("&").append(getDate(oldObject.getBillsignplandate())).append(" → ").append(getDate(newObject.getBillsignplandate()));
        }
        if (null != oldObject.getBillsignactualdate() && null != newObject.getBillsignactualdate() &&
                !oldObject.getBillsignactualdate().equals(newObject.getBillsignactualdate())) {
            titleBuilder.append("&实际签单日期");
            contentBuilder.append("&").append(getDate(oldObject.getBillsignactualdate())).append(" → ").append(getDate(newObject.getBillsignactualdate()));
        }
        if (null != oldObject.getCommercialplandate() && null != newObject.getCommercialplandate() &&
                !oldObject.getCommercialplandate().equals(newObject.getCommercialplandate())) {
            titleBuilder.append("&预计商用日期");
            contentBuilder.append("&").append(getDate(oldObject.getCommercialplandate())).append(" → ").append(getDate(newObject.getCommercialplandate()));
        }
        if (null != oldObject.getCommercialactualdate() && null != newObject.getCommercialactualdate() &&
                !oldObject.getCommercialactualdate().equals(newObject.getCommercialactualdate())) {
            titleBuilder.append("&实际商用日期");
            contentBuilder.append("&").append(getDate(oldObject.getCommercialactualdate())).append(" → ").append(getDate(newObject.getCommercialactualdate()));
        }
        if (!oldObject.getMajordomo().equals(newObject.getMajordomo())) {
            titleBuilder.append("&项目总监");
            contentBuilder.append("&").append(oldObject.getMajordomo()).append(" → ").append(newObject.getMajordomo());
        }
        if (!oldObject.getDeputymajordomo().equals(newObject.getDeputymajordomo())) {
            titleBuilder.append("&项目副总监");
            contentBuilder.append("&").append(oldObject.getDeputymajordomo()).append(" → ").append(newObject.getDeputymajordomo());
        }
        if (!oldObject.getProjectcustomer().equals(newObject.getProjectcustomer())) {
            titleBuilder.append("&项目客户");
            contentBuilder.append("&").append(oldObject.getProjectcustomer()).append(" → ").append(newObject.getProjectcustomer());
        }
        if (!oldObject.getCustomerabbreviation().equals(newObject.getCustomerabbreviation())) {
            titleBuilder.append("&客户简称");
            contentBuilder.append("&").append(oldObject.getCustomerabbreviation()).append(" → ").append(newObject.getCustomerabbreviation());
        }
        if (!oldObject.getProjectnumber().equals(newObject.getProjectnumber())) {
            titleBuilder.append("&项目编号");
            contentBuilder.append("&").append(oldObject.getProjectnumber()).append(" → ").append(newObject.getProjectnumber());
        }
        if (!oldObject.getProjectabbreviation().equals(newObject.getProjectabbreviation())) {
            titleBuilder.append("&项目简称");
            contentBuilder.append("&").append(oldObject.getProjectabbreviation()).append(" → ").append(newObject.getProjectabbreviation());
        }
        if (!oldObject.getProjectowner().equals(newObject.getProjectowner())) {
            titleBuilder.append("&项目所有人");
            contentBuilder.append("&").append(oldObject.getProjectowner()).append(" → ").append(newObject.getProjectowner());
        }
        if (!oldObject.getTotalbudgetmoney().equals(newObject.getTotalbudgetmoney())) {
            titleBuilder.append("&预计销售额");
            contentBuilder.append("&").append(oldObject.getTotalbudgetmoney()).append(" → ").append(newObject.getTotalbudgetmoney());
        }
        if (!oldObject.getBudgetmoney_1().equals(newObject.getBudgetmoney_1())) {
            titleBuilder.append("&下一年预算额度");
            contentBuilder.append("&").append(oldObject.getBudgetmoney_1()).append(" → ").append(newObject.getBudgetmoney_1());
        }
        if (!oldObject.getBudgetmoney_2().equals(newObject.getBudgetmoney_2())) {
            titleBuilder.append("&下下年预算额度");
            contentBuilder.append("&").append(oldObject.getBudgetmoney_2()).append(" → ").append(newObject.getBudgetmoney_2());
        }
//        int salesmanagerid = 0; //销售经理 ID
//        int projectcustomerid = 0; //项目客户ID
//        if(!oldObject.().equals(newObject.())){
//            titleBuilder.append("");
//            contentBuilder.append(oldObject.()).append(" → ").append(newObject.());
//        }

        String title = titleBuilder.toString();
        String content = contentBuilder.toString();
        if (!(StringUtils.isEmpty(title) || StringUtils.isEmpty(content))) {
            map.put(HISTORY_TITLE, title);
            map.put(HISTORY_CONTENT, content);
        }
        return map;
    }

    public List<ProjectByWeekly> getMyProject(Map<String, Long> map) throws RestException {
        return mProjectEntityMapper.getAllProjectByWeekly(map);
    }

    private String getDate(Timestamp time) {
        return DateUtil.format(time, "yyyy-MM-dd");
    }
}
