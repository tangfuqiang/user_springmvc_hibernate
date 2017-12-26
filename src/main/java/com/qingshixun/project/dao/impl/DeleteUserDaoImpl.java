package com.qingshixun.project.dao.impl;

import com.qingshixun.project.dao.DeleteUserDao;
import com.qingshixun.project.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DeleteUserDaoImpl implements DeleteUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void removeUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        UserModel userModel = session.get(UserModel.class,id);
        session.delete(userModel);
    }

    @Transactional
    @Override
    public void removeSomeUser(long[] ids) {
        Session session = sessionFactory.getCurrentSession();
        for(int i= 0 ;i<ids.length;i++) {
            UserModel userModel = session.get(UserModel.class,ids[i]);
            session.delete(userModel);
        }
    }
}
