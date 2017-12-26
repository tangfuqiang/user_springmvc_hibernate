package com.qingshixun.project.service;

import com.qingshixun.project.dao.impl.AddUserDaoImpl;
import com.qingshixun.project.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddUserService {
    @Autowired
    private AddUserDaoImpl addUserDao;

    public void addUser(UserModel userModel){
        addUserDao.addUser(userModel);
    }
}
