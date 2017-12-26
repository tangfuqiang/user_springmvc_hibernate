package com.qingshixun.project.dao;

public interface DeleteUserDao {
    void removeUser(long id);
    void removeSomeUser(long[] ids);
}
