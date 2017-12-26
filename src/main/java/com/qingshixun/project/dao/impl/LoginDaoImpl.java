package com.qingshixun.project.dao.impl;

import com.qingshixun.project.dao.LoginDao;
import com.qingshixun.project.model.AdminModel;
import com.qingshixun.project.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public AdminModel loginCheck(String username, String password) {
        String sql="from AdminModel where adminname=? and password = ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter(0,username);
        query.setParameter(1,password);
        return (AdminModel)query.uniqueResult();
    }

    @Transactional
    @Override
    public List<UserModel> loginPass(int startpage, int maxpage) {
        String sql = "from UserModel";
        Session session =sessionFactory.getCurrentSession();
        Query query =  session.createQuery(sql);
        query.setFirstResult(startpage);
        query.setMaxResults(maxpage);
        return (List<UserModel>) query.list();
    }
}
