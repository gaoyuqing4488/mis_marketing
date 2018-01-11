package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.clue.*;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by GYQ on 2018/1/3.
 */
public interface ClueService {

    public Integer addClue(Integer userId, Clue clue, HttpSession session) throws RestException;

    public ClueListResponse getAllClues(ClueListReq clueListReq, HttpSession session) throws RestException;

    public ClueInfo getClueInfoById(Integer userid, Integer id, HttpSession session) throws RestException;

    public List<ClueModifyHistory> getAllClueHistorys(Integer clueid) throws RestException;

    @Transactional(rollbackFor = Exception.class)
    public void updateClue(UpdateClueInfoReq updateClueInfoReq, HttpSession session) throws RestException;
}
