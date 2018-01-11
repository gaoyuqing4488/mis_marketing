package com.misrobot.mismarketing.pojo.clue;

import java.util.List;

/**
 * Created by GYQ on 2018/1/4.
 */
public class ClueInfo {
    private List<ClueModifyHistory> clueModifyHistories;

    private ClueList clueList;

    public ClueList getClueList() {
        return clueList;
    }

    public void setClueList(ClueList clueList) {
        this.clueList = clueList;
    }

    public List<ClueModifyHistory> getClueModifyHistories() {
        return clueModifyHistories;
    }

    public void setClueModifyHistories(List<ClueModifyHistory> clueModifyHistories) {
        this.clueModifyHistories = clueModifyHistories;
    }
}
