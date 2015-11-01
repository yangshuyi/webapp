package com.echodrama.dao.impl;

import com.echodrama.dao.HibernateStatisticsDao;
import com.echodrama.model.HibernateStatistics;
import org.hibernate.SessionFactory;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/7/14
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository("hibernateStatisticsDaoImpl")
public class HibernateStatisticsDaoImpl implements HibernateStatisticsDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public HibernateStatistics queryHibernateStatistics() {
        HibernateStatistics hibernateStatistics = new HibernateStatistics();

        SessionFactory sessionFactory = this.sessionFactory.getCurrentSession().getSessionFactory();
        String[] regionNames = sessionFactory.getStatistics().getSecondLevelCacheRegionNames();

        for (String regionName : regionNames) {
            SecondLevelCacheStatistics secondLevelCacheStatistics = sessionFactory.getStatistics().getSecondLevelCacheStatistics(regionName);

            HibernateStatistics.CacheStatistics cacheStatistics = hibernateStatistics.new CacheStatistics();
            cacheStatistics.setHitCount(secondLevelCacheStatistics.getHitCount());
            cacheStatistics.setMissCount(secondLevelCacheStatistics.getMissCount());
            cacheStatistics.setPutCount(secondLevelCacheStatistics.getPutCount());
            cacheStatistics.setElementCountInMemory(secondLevelCacheStatistics.getElementCountInMemory());
            cacheStatistics.setSizeInMemory(secondLevelCacheStatistics.getSizeInMemory());

            hibernateStatistics.addCacheStatistics(regionName, cacheStatistics);
        }

        return hibernateStatistics;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
