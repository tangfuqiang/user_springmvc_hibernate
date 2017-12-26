package com.qingshixun.project.dao.impl;

import com.qingshixun.project.dao.AddUserDao;
import com.qingshixun.project.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AddUserDaoImpl implements AddUserDao{

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @Override
    public void addUser(UserModel userModel) {
        Session session =sessionFactory.getCurrentSession();
        session.save(userModel);
    }
}
