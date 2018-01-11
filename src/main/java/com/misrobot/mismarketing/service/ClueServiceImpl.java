package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.constants.*;
import com.misrobot.mismarketing.dao.clue.ClueDao;
import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.clue.*;
import com.misrobot.mismarketing.util.ErrorMessageUtil;
import com.misrobot.mismarketing.util.TokenUtil;
import com.sun.istack.internal.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by GYQ on 2018/1/3.
 */
@Service
public class ClueServiceImpl extends AbstractService implements ClueService {
    private static final Logger log = LoggerFactory.getLogger(ClueServiceImpl.class);
    @Autowired
    private ClueDao clueDao;

    @Override
    public Integer addClue(Integer userId, Clue clue, HttpSession session) throws RestException {
        Integer result = 0;
        try {
            if (TokenUtil.checkLoginInfo(Long.valueOf(userId), session)) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("companyName", clue.getCompanyName());
                int count = clueDao.getCountsByCompanyName(map);
                if (count >= 1) {
                    result = 2;
                    return result;
                }
                result = clueDao.addClue(clue);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return result;
    }

    @Override
    public ClueListResponse getAllClues(ClueListReq clueListReq, HttpSession session) throws RestException {
        Integer selectType = clueListReq.getSelectType();
        Integer type = clueListReq.getType();
        Integer userId = clueListReq.getUserid();
        ClueListResponse clueListResponse = new ClueListResponse();
        try {
            if (TokenUtil.checkLoginInfo(Long.valueOf(userId), session)) {
                if (MismarketingUserType.MANAGE.getValue() == type && SelectType.ALL.getValue() == selectType) {
                    clueListReq.setUserid(null);
                }
                List<ClueList> clueLists = clueDao.getAllClues(clueListReq);
                Integer counts = clueDao.getClueCounts(clueListReq);
                clueListResponse.setCounts(counts);
                clueListResponse.setClueLists(clueLists);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return clueListResponse;
    }

    @Override
    public ClueInfo getClueInfoById(Integer userid, Integer id, HttpSession session) throws RestException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        ClueList clueList = new ClueList();
        ClueInfo clueInfo = new ClueInfo();
        try {
            if (TokenUtil.checkLoginInfo(Long.valueOf(userid), session)) {
                clueList = clueDao.getClueInfoById(map);
                List<ClueModifyHistory> list = clueDao.getAllClueHistorys(id);
                clueInfo.setClueModifyHistories(list);
                clueInfo.setClueList(clueList);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return clueInfo;
    }

    @Override
    public List<ClueModifyHistory> getAllClueHistorys(Integer clueid) throws RestException {
        List<ClueModifyHistory> list = new ArrayList<ClueModifyHistory>();
        try {
            list = clueDao.getAllClueHistorys(clueid);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }
        return list;
    }

    @Override
    public void updateClue(UpdateClueInfoReq updateClueInfoReq, HttpSession session) throws RestException {
        String loginid = updateClueInfoReq.getLoginid();
        ClueList clue = new ClueList();
        clue.setId(updateClueInfoReq.getId());
        clue.setCompanyName(updateClueInfoReq.getCompanyName());
        clue.setDistrictId(updateClueInfoReq.getDistrictId());
        clue.setDistrictname(updateClueInfoReq.getDistrictname());
        clue.setContactName(updateClueInfoReq.getContactName());
        clue.setCustomerType(updateClueInfoReq.getCustomerType());
        clue.setDepartment(updateClueInfoReq.getDepartment());
        clue.setTelephone(updateClueInfoReq.getTelephone());
        clue.setCustomerRate(updateClueInfoReq.getCustomerRate());
        clue.setCustomerInitiative(updateClueInfoReq.getCustomerInitiative());
        clue.setInformationSources(updateClueInfoReq.getInformationSources());
        clue.setClueInfo(updateClueInfoReq.getClueInfo());
        clue.setProgress(updateClueInfoReq.getProgress());
        clue.setCreaterId(updateClueInfoReq.getCreaterId());
        clue.setManagerId(updateClueInfoReq.getManagerId());
        clue.setManagername(updateClueInfoReq.getManagername());
        clue.setStatus(updateClueInfoReq.getStatus());
        clue.setLevel(updateClueInfoReq.getLevel());
        clue.setUpdateTime(new Date());
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", clue.getId());
        try {
            ClueList oldClue = clueDao.getClueInfoById(map);
            Map<String, String> differMap = getDifference(oldClue, clue);
            ClueModifyHistory clueModifyHistory = new ClueModifyHistory();
            if (clue.getId() != null) {
                clueModifyHistory.setClueId(clue.getId());
            }
            clueModifyHistory.setModifyContent(differMap.get("content"));
            clueModifyHistory.setTitle(differMap.get("title"));
            if (updateClueInfoReq.getUserId() != null) {
                clueModifyHistory.setUserId(updateClueInfoReq.getUserId());
            }
            clueModifyHistory.setModifyDate(new Date());
            if (TokenUtil.checkLoginInfo(Long.valueOf(loginid), session)) {
                clueDao.updateClue(clue);
                clueDao.addClueHistory(clueModifyHistory);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new RestException(ErrorCode.DATABASE_EXCEPTION,
                    ErrorMessageUtil.getMessage(ErrorCode.DATABASE_EXCEPTION));
        }

    }

    /**
     * 修改前后对比线索
     *
     * @param oldObject
     * @param newObject
     * @return
     */
    private Map getDifference(@Nullable ClueList oldObject, @Nullable ClueList newObject) {
        Map map = new HashMap();
        StringBuilder titleBuilder = new StringBuilder();
        StringBuilder contentBuilder = new StringBuilder();
        if (!oldObject.getCompanyName().equals(newObject.getCompanyName())) {
            titleBuilder.append("客户单位名称");
            contentBuilder.append(oldObject.getCompanyName()).append(" → ").append(newObject.getCompanyName());
        }
        if (!oldObject.getContactName().equals(newObject.getContactName())) {
            titleBuilder.append("&客户联系人");
            contentBuilder.append("&").append(oldObject.getContactName()).append(" → ").append(newObject.getContactName());
        }
        if (oldObject.getCreaterId() != newObject.getCreaterId()) {
            titleBuilder.append("&录入人员");
            contentBuilder.append("&").append(oldObject.getCreatername()).append(" → ").append(newObject.getCreatername());
        }
        if (oldObject.getCustomerInitiative() != newObject.getCustomerInitiative()) {
            String oldCustomerInitiative = "";
            String newCustomerInitiative = "";
            titleBuilder.append("&客户主动性");
            switch (oldObject.getCustomerInitiative()) {
                case 0:
                    oldCustomerInitiative = CustomerInitiative.CUSTOMER.getDesc();
                    break;
                case 1:
                    oldCustomerInitiative = CustomerInitiative.OUR.getDesc();
                    break;
            }
            switch (newObject.getCustomerInitiative()) {
                case 0:
                    newCustomerInitiative = CustomerInitiative.CUSTOMER.getDesc();
                    break;
                case 1:
                    newCustomerInitiative = CustomerInitiative.OUR.getDesc();
                    break;
            }
            contentBuilder.append("&").append(oldCustomerInitiative).append(" → ").append(newCustomerInitiative);
        }
        if (oldObject.getInformationSources() != newObject.getInformationSources()) {
            String oldInformationSources = "";
            String newInformationSources = "";
            titleBuilder.append("&信息来源");
            switch (oldObject.getInformationSources()) {
                case 0:
                    oldInformationSources = InformationSources.EXHIBITION.getDesc();
                    break;
                case 1:
                    oldInformationSources = InformationSources.VISIT.getDesc();
                    break;
                case 2:
                    oldInformationSources = InformationSources.TELEPHONE.getDesc();
                    break;
                case 3:
                    oldInformationSources = InformationSources.INTRODUCTION.getDesc();
                    break;
            }
            switch (newObject.getInformationSources()) {
                case 0:
                    newInformationSources = InformationSources.EXHIBITION.getDesc();
                    break;
                case 1:
                    newInformationSources = InformationSources.VISIT.getDesc();
                    break;
                case 2:
                    newInformationSources = InformationSources.TELEPHONE.getDesc();
                    break;
                case 3:
                    newInformationSources = InformationSources.INTRODUCTION.getDesc();
                    break;
            }
            contentBuilder.append("&").append(oldInformationSources).append(" → ").append(newInformationSources);
        }
        if (!oldObject.getClueInfo().equals(newObject.getClueInfo())) {
            titleBuilder.append("&线索信息");
            contentBuilder.append("&").append(oldObject.getClueInfo()).append(" → ").append(newObject.getClueInfo());
        }
        if (!oldObject.getProgress().equals(newObject.getProgress())) {
            titleBuilder.append("&进展情况");
            contentBuilder.append("&").append(oldObject.getProgress()).append(" → ").append(newObject.getProgress());
        }
        if (!oldObject.getDistrictname().equals(newObject.getDistrictname())) {
            titleBuilder.append("&所属省份");
            contentBuilder.append("&").append(oldObject.getDistrictname()).append(" → ").append(newObject.getDistrictname());
        }
        if (!oldObject.getManagername().equals(newObject.getManagername())) {
            titleBuilder.append("&负责人");
            contentBuilder.append("&").append(oldObject.getManagername()).append(" → ").append(newObject.getManagername());
        }
        if (oldObject.getCustomerRate() != newObject.getCustomerRate()) {
            String oldCustomerRateName = "";
            String newCustomerRateName = "";
            titleBuilder.append("&接触客户级别");
            switch (oldObject.getCustomerRate()) {
                case 0:
                    oldCustomerRateName = CustomerRate.HEADMASTER.getDesc();
                    break;
                case 1:
                    oldCustomerRateName = CustomerRate.DEAN.getDesc();
                    break;
                case 2:
                    oldCustomerRateName = CustomerRate.DIRECTOR.getDesc();
                    break;
                case 3:
                    oldCustomerRateName = CustomerRate.TEACHER.getDesc();
                    break;
                case 4:
                    oldCustomerRateName = CustomerRate.OTHER.getDesc();
                    break;
            }
            switch (newObject.getCustomerRate()) {
                case 0:
                    newCustomerRateName = CustomerRate.HEADMASTER.getDesc();
                    break;
                case 1:
                    newCustomerRateName = CustomerRate.DEAN.getDesc();
                    break;
                case 2:
                    newCustomerRateName = CustomerRate.DIRECTOR.getDesc();
                    break;
                case 3:
                    newCustomerRateName = CustomerRate.TEACHER.getDesc();
                    break;
                case 4:
                    newCustomerRateName = CustomerRate.OTHER.getDesc();
                    break;
            }
            contentBuilder.append("&").append(oldCustomerRateName).append(" → ").append(newCustomerRateName);
        }
        if (!oldObject.getDepartment().equals(newObject.getDepartment())) {
            titleBuilder.append("&业务部");
            contentBuilder.append("&").append(oldObject.getDepartment()).append(" → ").append(newObject.getDepartment());
        }
        if (!oldObject.getTelephone().equals(newObject.getTelephone())) {
            titleBuilder.append("&联系方式");
            contentBuilder.append("&").append(oldObject.getTelephone()).append(" → ").append(newObject.getTelephone());
        }
        if (!oldObject.getLevel().equals(newObject.getLevel())) {
            titleBuilder.append("&客户等级");
            String oldLevel = "";
            String newLevel = "";
            switch (oldObject.getLevel()) {
                case 0:
                    oldLevel = Level.PROVINCE.getDesc();
                    break;
                case 1:
                    oldLevel = Level.CITY.getDesc();
                    break;
                case 2:
                    oldLevel = Level.PART.getDesc();
                    break;
                case 3:
                    oldLevel = Level.COOPERATE.getDesc();
                    break;
                case 4:
                    oldLevel = Level.PRIVATE.getDesc();
                    break;
                case 5:
                    oldLevel = Level.THREECLASSA.getDesc();
                    break;
                case 6:
                    oldLevel = Level.THREECLASSB.getDesc();
                    break;
                case 7:
                    oldLevel = Level.SECOND.getDesc();
                    break;
                case 8:
                    oldLevel = Level.OTHER.getDesc();
                    break;
            }
            switch (newObject.getLevel()) {
                case 0:
                    newLevel = Level.PROVINCE.getDesc();
                    break;
                case 1:
                    newLevel = Level.CITY.getDesc();
                    break;
                case 2:
                    newLevel = Level.PART.getDesc();
                    break;
                case 3:
                    newLevel = Level.COOPERATE.getDesc();
                    break;
                case 4:
                    newLevel = Level.PRIVATE.getDesc();
                    break;
                case 5:
                    newLevel = Level.THREECLASSA.getDesc();
                    break;
                case 6:
                    newLevel = Level.THREECLASSB.getDesc();
                    break;
                case 7:
                    newLevel = Level.SECOND.getDesc();
                    break;
                case 8:
                    newLevel = Level.OTHER.getDesc();
                    break;
            }
            contentBuilder.append("&").append(oldLevel).append(" → ").append(newLevel);
        }
        if (!oldObject.getStatus().equals(newObject.getStatus())) {
            String oldStatus = "";
            String newStatus = "";
            titleBuilder.append("&状态");
            switch (oldObject.getStatus()) {
                case 0:
                    oldStatus = ClueStatus.FOLLOWING.getDesc();
                    break;
                case 1:
                    oldStatus = ClueStatus.CLOSEREACTION.getDesc();
                    break;
                case 2:
                    oldStatus = ClueStatus.NOREACTIONCLOSE.getDesc();
                    break;
                case 3:
                    oldStatus = ClueStatus.CLOSECLUE.getDesc();
                    break;
            }
            switch (newObject.getStatus()) {
                case 0:
                    newStatus = ClueStatus.FOLLOWING.getDesc();
                    break;
                case 1:
                    newStatus = ClueStatus.CLOSEREACTION.getDesc();
                    break;
                case 2:
                    newStatus = ClueStatus.NOREACTIONCLOSE.getDesc();
                    break;
                case 3:
                    newStatus = ClueStatus.CLOSECLUE.getDesc();
                    break;
            }
            contentBuilder.append("&").append(oldStatus).append(" → ").append(newStatus);
        }
        String title = titleBuilder.toString();
        String content = contentBuilder.toString();
        if (!(StringUtils.isEmpty(title) || StringUtils.isEmpty(content))) {
            map.put("title", title);
            map.put("content", content);
        }
        return map;
    }
}
