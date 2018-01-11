package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.enroll.Enroll;

import javax.servlet.http.HttpSession;

/**
 * Created by GYQ on 2018/1/9.
 */
public interface EnrollService {

    public Integer addEnroll(Enroll enroll, HttpSession session)throws RestException;
}
