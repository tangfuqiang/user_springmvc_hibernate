package com.qingshixun.project.dao.impl;

import com.qingshixun.project.dao.AddOccupationDao;
import com.qingshixun.project.model.OccupationModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class AddOccupationDaoImpl implements AddOccupationDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void addOccupation(OccupationModel occupation) {
        Session session = sessionFactory.getCurrentSession();
        session.save(occupation);
    }
}
