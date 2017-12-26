package com.qingshixun.project.service;

import com.qingshixun.project.dao.impl.TotalPageDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalPageService {
    @Autowired
    private TotalPageDaoImpl totalPageDao;

    public int getTotalPage(int maxPgae) {
        return totalPageDao.getTotalPage(maxPgae);
    }
}
