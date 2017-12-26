package com.qingshixun.project.service;

import com.qingshixun.project.dao.impl.AddOccupationDaoImpl;
import com.qingshixun.project.model.OccupationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddOccupationService {
    @Autowired
    private AddOccupationDaoImpl addOccupationDao;
    public void addOccupation(OccupationModel occupation){
        addOccupationDao.addOccupation(occupation);
    }
}
