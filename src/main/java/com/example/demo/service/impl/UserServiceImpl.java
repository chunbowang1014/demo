package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String getUserInfo() {
        List<User> userList = userDao.findAll();
        userList.forEach(user -> logger.info(user.getUsername()));
        return "这个人的用户信息";
    }

    @Override
    public String UserInfoList() {
        return "用户List";
    }

    @Override
    @Transactional
    public String updateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("王" + new Random().nextInt(10));
        userDao.save(user);
        return "OK";
    }
}
