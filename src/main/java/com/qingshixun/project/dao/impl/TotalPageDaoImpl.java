package com.qingshixun.project.dao.impl;

import com.qingshixun.project.dao.TotalPageDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class TotalPageDaoImpl implements TotalPageDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public int getTotalPage(int maxPgae) {
        String sql = "select count(*) from UserModel";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        long totaluser = (long) query.uniqueResult();
        int totalPage;
        if (totaluser % 5 == 0) {
            totalPage = (int) totaluser / 5;
        } else {
            totalPage = (int) totaluser / 5 + 1;
        }
        return totalPage;
    }
}
