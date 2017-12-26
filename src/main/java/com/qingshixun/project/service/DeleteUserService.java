package com.qingshixun.project.service;

import com.qingshixun.project.dao.impl.DeleteUserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {

    @Autowired
    private DeleteUserDaoImpl deleteUserDao;

    public void removeUser(long id) {
        deleteUserDao.removeUser(id);
    }

    public void removeSomeUser(long[] ids){
        deleteUserDao.removeSomeUser(ids);
    }
}
