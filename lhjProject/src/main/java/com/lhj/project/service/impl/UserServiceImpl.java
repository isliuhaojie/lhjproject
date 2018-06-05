package com.lhj.project.service.impl;

import com.lhj.project.dao.IUserDao;
import com.lhj.project.model.User;
import com.lhj.project.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {
        return this.userDao.selectUser(userId);
    }

}