package com.qingshixun.project.dao;

import java.util.Date;

public interface UpdateUserDao {
    void updateUser(long id,String username, String occupation,String hobby, String email, char sex, Date birthday);
}
