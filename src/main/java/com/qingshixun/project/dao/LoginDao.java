package com.qingshixun.project.dao;

import com.qingshixun.project.model.AdminModel;
import com.qingshixun.project.model.UserModel;

import java.util.List;

public interface LoginDao {
    AdminModel loginCheck(String username,String password);
    List<UserModel> loginPass(int startpage,int maxpage);
}
