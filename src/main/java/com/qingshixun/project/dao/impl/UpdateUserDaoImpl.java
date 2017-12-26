package com.qingshixun.project.dao.impl;

import com.qingshixun.project.dao.UpdateUserDao;
import com.qingshixun.project.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public class UpdateUserDaoImpl implements UpdateUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void updateUser(long id, String username, String occupation, String hobby, String email, char sex, Date birthday) {
        Session session = sessionFactory.getCurrentSession();
        UserModel userModel = (UserModel) session.get(UserModel.class, id);
        userModel.setBirthday(birthday);
        userModel.setEmail(email);
        userModel.setHobby(hobby);
        userModel.setOccupation(occupation);
        userModel.setUsername(username);
        userModel.setSex(sex);

        session.update(userModel);
    }
}
