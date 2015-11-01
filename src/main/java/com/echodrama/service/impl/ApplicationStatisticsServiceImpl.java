package com.echodrama.service.impl;

import com.echodrama.dao.HibernateStatisticsDao;
import com.echodrama.model.HibernateStatistics;
import com.echodrama.service.ApplicationStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/7/14
 * Time: 9:33 AM
 * To change this template use File | Settings | File Templates.
 */

@Service("applicationStatisticsServiceImpl")
public class ApplicationStatisticsServiceImpl implements ApplicationStatisticsService {
    @Autowired
    private HibernateStatisticsDao hibernateStatisticsDao;

    @Override
    public HibernateStatistics queryHibernateStatistics() throws Exception {
        return hibernateStatisticsDao.queryHibernateStatistics();
    }

    public HibernateStatisticsDao getHibernateStatisticsDao() {
        return hibernateStatisticsDao;
    }

    public void setHibernateStatisticsDao(HibernateStatisticsDao hibernateStatisticsDao) {
        this.hibernateStatisticsDao = hibernateStatisticsDao;
    }
}
