package com.misrobot.mismarketing.dao.project;

import com.misrobot.mismarketing.entity.CustomerEntity;
import com.misrobot.mismarketing.entity.ProjectEntity;
import com.misrobot.mismarketing.pojo.project.FirstFewProject;
import com.misrobot.mismarketing.pojo.project.ProjectByWeekly;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by CHJ on 2017/8/1.
 */
@Repository
public interface ProjectEntityMapper {
    int deleteByPrimaryKey(Integer userId);

    int deleteByCustomerID(Integer customerId);

    int insert(ProjectEntity record);

    int insertSelective(ProjectEntity record);

    List<ProjectEntity> selectByPrimaryKey(int id);

    int getProjectsCountByPrimaryKey(int id);

    List<ProjectEntity> findAllProjectsByUserID(@Param("userid") int userid, @Param("offset") int offset, @Param("pagesize") int pagesize);

    int getProjectsCountByUserID(int userid);

    List<ProjectEntity> findAllProjectsByCustomerID(@Param("customerid") int customerid, @Param("offset") int offset, @Param("pagesize") int pagesize);

    List<ProjectEntity> findAllProjects(@Param("offset") int offset, @Param("pagesize") int pagesize);

    List<ProjectEntity> findByProjectNumber(String projectnumber);

    int getAllProjectsCount();

    Integer getCustomerIDByPrimaryKey(Integer id);

    Integer updateByPrimaryKey(ProjectEntity record);

    /*我的所有项目*/
    List<ProjectByWeekly> getAllProjectByWeekly(Map<String, Long> map);

    /*一级项目*/
    List<FirstFewProject> getFirstProject() throws SQLException;

    /*二级项目*/
    List<FirstFewProject> getSecondProject() throws SQLException;

    /*重大项目*/
    List<FirstFewProject> getImportProject() throws SQLException;

    /*批量修改项目*/
    Integer updateProjectInfos(List<ProjectEntity> list) throws SQLException;
}
