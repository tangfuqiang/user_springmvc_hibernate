package com.qingshixun.project.service;

import com.qingshixun.project.dao.impl.LoginDaoImpl;
import com.qingshixun.project.model.AdminModel;
import com.qingshixun.project.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private LoginDaoImpl loginDao;

    public AdminModel loginCheck(String username,String password){
        return loginDao.loginCheck(username,password);
    }

    public List<UserModel> loginPass(int startPage,int maxPage){
        return loginDao.loginPass(startPage,maxPage);
    }
}
