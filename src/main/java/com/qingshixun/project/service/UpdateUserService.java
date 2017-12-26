package com.qingshixun.project.service;

import com.qingshixun.project.dao.impl.UpdateUserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UpdateUserService {

    @Autowired
    private UpdateUserDaoImpl updateUserDao;

    public void updateUser(long id, String username, String occupation, String hobby, String email, char sex, Date birthday){
        updateUserDao.updateUser(id,username,occupation,hobby,email,sex,birthday);
    }
}
