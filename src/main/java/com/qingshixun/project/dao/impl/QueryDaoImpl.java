package com.qingshixun.project.dao.impl;

import com.qingshixun.project.dao.QueryDao;
import com.qingshixun.project.model.AdminModel;
import com.qingshixun.project.model.HobbyModel;
import com.qingshixun.project.model.OccupationModel;
import com.qingshixun.project.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class QueryDaoImpl implements QueryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public UserModel queryUsername(String username) {

        String sql = "from UserModel where username=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter(0, username);

        return (UserModel) query.uniqueResult();
    }

    @Transactional
    @Override
    public List<HobbyModel> queryAllHobby() {
        String sql = "from HobbyModel";
        Session session = sessionFactory.getCurrentSession();
        Query<HobbyModel> query = session.createQuery(sql);
        return (List<HobbyModel>) query.list();
    }

    @Transactional
    @Override
    public List<OccupationModel> queryAllOccupation() {
        String sql = "from OccupationModel";
        Session session = sessionFactory.getCurrentSession();
        Query<OccupationModel> query = session.createQuery(sql);
        return (List<OccupationModel>) query.list();
    }

    @Transactional
    @Override
    public List<UserModel> queryLikeUsername(String username,int pageNo,int maxPage) {
        String sql = "from UserModel where username like ?";
        Session session =sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setFirstResult(pageNo);
        query.setMaxResults(maxPage);
        query.setParameter(0,"%"+username+"%");
        return (List<UserModel>)query.list();
    }

    @Transactional
    @Override
    public long getQueryTotalPage(int maxPage, String username) {
        String sql = "select count(*) from UserModel where username like ?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter(0,"%"+username+"%");
        long total = (long)query.uniqueResult();
        if(total%maxPage==0){
            return total/maxPage;
        }else {
            return total/maxPage+1;
        }
    }

    @Transactional
    @Override
    public HobbyModel queryHobby(String hobby) {
        String sql = "from HobbyModel where hobby=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter(0,hobby);
        return (HobbyModel)query.uniqueResult();
    }

    @Transactional
    @Override
    public OccupationModel queryOccupation(String occupation) {
        String sql = "from OccupationModel where occupation=?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter(0,occupation);
        return (OccupationModel)query.uniqueResult();

    }

    @Transactional
    @Override
    public AdminModel queryAdminMoodel(String username) {
        String sql = "from AdminModel where adminname =?";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        query.setParameter(0,username);
        return (AdminModel)query.uniqueResult();
    }
}
