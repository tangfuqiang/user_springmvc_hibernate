package com.qingshixun.project.dao;

import com.qingshixun.project.model.AdminModel;
import com.qingshixun.project.model.HobbyModel;
import com.qingshixun.project.model.OccupationModel;
import com.qingshixun.project.model.UserModel;

import java.util.List;

public interface QueryDao {
    UserModel queryUsername(String username);
    List<HobbyModel> queryAllHobby();
    List<OccupationModel> queryAllOccupation();
    List<UserModel> queryLikeUsername(String username,int pageNo,int maxPage);
    long getQueryTotalPage(int maxPage ,String username);
    HobbyModel queryHobby(String hobby);
    OccupationModel queryOccupation(String occupation);
    AdminModel queryAdminMoodel(String username);
}
