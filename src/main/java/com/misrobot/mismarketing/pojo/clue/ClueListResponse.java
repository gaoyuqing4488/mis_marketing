package com.misrobot.mismarketing.pojo.clue;

import java.util.List;

/**
 * Created by GYQ on 2018/1/4.
 */
public class ClueListResponse  {

    private List<ClueList> clueLists;

    private Integer counts;

    public List<ClueList> getClueLists() {
        return clueLists;
    }

    public void setClueLists(List<ClueList> clueLists) {
        this.clueLists = clueLists;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
