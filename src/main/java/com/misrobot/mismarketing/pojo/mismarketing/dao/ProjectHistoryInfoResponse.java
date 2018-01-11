package com.misrobot.mismarketing.pojo.mismarketing.dao;

import com.misrobot.mismarketing.pojo.BaseResponse;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by CHJ on 2017/9/28.
 */
public class ProjectHistoryInfoResponse<T> extends BaseResponse {

    public ArrayList<T> queryprojecthistorylist = new ArrayList<>();

    public ArrayList<T> getQueryprojecthistorylist() {
        return queryprojecthistorylist;
    }

    public void setQueryprojecthistorylist(ArrayList<T> queryprojecthistorylist) {
        this.queryprojecthistorylist = queryprojecthistorylist;
    }

    public static class ProjectHistoryInfoUnit {
        private int projectid;
        private Timestamp modifydate;
        private String title;
        private String modifycontent;

        public int getProjectid() {
            return projectid;
        }

        public Timestamp getModifydate() {
            return modifydate;
        }

        public String getTitle() {
            return title;
        }

        public String getModifycontent() {
            return modifycontent;
        }

        public void setProjectid(int projectid) {
            this.projectid = projectid;
        }

        public void setModifydate(Timestamp modifydate) {
            this.modifydate = modifydate;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setModifycontent(String modifycontent) {
            this.modifycontent = modifycontent;
        }
    }
}
