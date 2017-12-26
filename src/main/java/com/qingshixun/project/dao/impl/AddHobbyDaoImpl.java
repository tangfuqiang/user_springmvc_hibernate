package com.qingshixun.project.dao.impl;

import com.qingshixun.project.dao.AddHobbyDao;
import com.qingshixun.project.model.HobbyModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class AddHobbyDaoImpl implements AddHobbyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void addHoddy(String hobby) {
        Session session = sessionFactory.getCurrentSession();
        HobbyModel hobbyModel = new HobbyModel();
        hobbyModel.setCreatetime(new Date());
        hobbyModel.setHobby(hobby);
        session.save(hobbyModel);
    }
}
