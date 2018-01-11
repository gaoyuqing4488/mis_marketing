package com.misrobot.mismarketing.dao.project;

import com.misrobot.mismarketing.entity.CustomerStatisticsEntity;
import com.misrobot.mismarketing.entity.ProjectStatisticsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CHJ on 2017/8/7.
 */
@Repository
public interface ProjectStatisticsEntityMapper {

    int deleteByPrimaryKey(Integer id);

    int deleteByProjectID(Integer projectid);

    int insert(ProjectStatisticsEntity record);

    int insertSelective(ProjectStatisticsEntity record);

    ProjectStatisticsEntity selectByPrimaryKey(Integer id);

    List<ProjectStatisticsEntity> findAllRecords();

    int updateByPrimaryKey(ProjectStatisticsEntity record);

    List<ProjectStatisticsEntity> findByUserID(Integer userid);

    int deleteByUserId(Integer userid);

    int updateByProjectID(ProjectStatisticsEntity record);

    void updateProjectStatisticsByProjectID(List<ProjectStatisticsEntity> list);
}
