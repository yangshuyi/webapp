package com.echodrama.service;

import com.echodrama.model.HibernateStatistics;

public interface ApplicationStatisticsService {
    public HibernateStatistics queryHibernateStatistics() throws Exception;
}
