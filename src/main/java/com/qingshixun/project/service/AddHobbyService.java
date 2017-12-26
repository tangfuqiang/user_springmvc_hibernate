package com.qingshixun.project.service;

import com.qingshixun.project.dao.impl.AddHobbyDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddHobbyService {

    @Autowired
    private AddHobbyDaoImpl addHobbyDao;

    public void addHobby(String hobby){
        addHobbyDao.addHoddy(hobby);
    }
}
