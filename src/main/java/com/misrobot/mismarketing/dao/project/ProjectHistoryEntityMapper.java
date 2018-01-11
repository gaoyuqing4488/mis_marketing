package com.misrobot.mismarketing.dao.project;

import com.misrobot.mismarketing.entity.CustomerEntity;
import com.misrobot.mismarketing.entity.ProjectHistoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CHJ on 2017/8/2.
 */
@Repository
public interface ProjectHistoryEntityMapper {
    int deleteByPrimaryKey(Integer userId);

    int deleteByProjectID(int projectid);

    int insert(ProjectHistoryEntity record);

    int insertSelective(ProjectHistoryEntity record);

    ProjectHistoryEntity selectByPrimaryKey(Integer userId);

    List<ProjectHistoryEntity> queryAllRecords();

    List<ProjectHistoryEntity> queryAllRecordsByProjectID(int projectid);

    int updateByPrimaryKey(ProjectHistoryEntity record);
}
