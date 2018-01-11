package com.misrobot.mismarketing.dao.clue;

import com.misrobot.mismarketing.pojo.clue.Clue;
import com.misrobot.mismarketing.pojo.clue.ClueList;
import com.misrobot.mismarketing.pojo.clue.ClueListReq;
import com.misrobot.mismarketing.pojo.clue.ClueModifyHistory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by GYQ on 2018/1/3.
 */
public interface ClueDao {

    int addClue(Clue clue) throws SQLException;

    List<ClueList> getAllClues(ClueListReq  clueListReq) throws SQLException;

    int getClueCounts(ClueListReq  clueListReq) throws SQLException;

    ClueList getClueInfoById(Map<String,Integer> map) throws SQLException;

    List<ClueModifyHistory> getAllClueHistorys(Integer clueid)throws SQLException;

    void updateClue(Clue clue)throws SQLException;

    int addClueHistory(ClueModifyHistory clueModifyHistory)throws SQLException;

    int getCountsByCompanyName(Map<String,String> map)throws SQLException;
}
