package com.qingshixun.project.service;

import com.qingshixun.project.dao.impl.QueryDaoImpl;
import com.qingshixun.project.model.AdminModel;
import com.qingshixun.project.model.HobbyModel;
import com.qingshixun.project.model.OccupationModel;
import com.qingshixun.project.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    @Autowired
    private QueryDaoImpl queryDao;

    public UserModel queryUsername(String username){
        return queryDao.queryUsername(username);
    }

    public List<HobbyModel> queryAllHobby(){
        return queryDao.queryAllHobby();
    }
    public List<OccupationModel> queryAllOccupation(){
        return queryDao.queryAllOccupation();
    }

    public List<UserModel> queryLikeUsername(String username,int pageNo,int maxPage){
        return queryDao.queryLikeUsername(username,pageNo,maxPage);
    }

    public long getQueryTotalPage(int maxPage ,String username){
        return queryDao.getQueryTotalPage(maxPage,username);
    }

    public HobbyModel queryHobby(String hobby){
        return queryDao.queryHobby(hobby);
    }

    public OccupationModel queryOccupation(String occupation){
        return queryDao.queryOccupation(occupation);
    }

    public AdminModel queryAdminMoodel(String username){
        return queryDao.queryAdminMoodel(username);
    }
}
