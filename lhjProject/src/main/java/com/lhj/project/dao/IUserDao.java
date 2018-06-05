package com.lhj.project.dao;

import com.lhj.project.model.User;

public interface IUserDao {

    User selectUser(long id);

}