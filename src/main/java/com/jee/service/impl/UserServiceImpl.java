package com.jee.service.impl;

import com.jee.dao.UserDao;
import com.jee.pojo.User;
import com.jee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User checkUser(String username) {
        return userDao.queryByUsername(username);
    }
}
