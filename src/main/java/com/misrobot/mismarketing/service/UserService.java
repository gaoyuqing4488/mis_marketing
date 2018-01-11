package com.misrobot.mismarketing.service;

import com.misrobot.mismarketing.exceptions.RestException;
import com.misrobot.mismarketing.pojo.enroll.AddUserRes;
import com.misrobot.mismarketing.pojo.enroll.User;

import javax.servlet.http.HttpSession;

/**
 * Created by GYQ on 2018/1/9.
 */
public interface UserService {

    public AddUserRes addUser(User user)throws RestException;

    public  AddUserRes loginUserInfo(User user, HttpSession session)throws RestException;
}
