package com.echodrama.web.action;

import com.echodrama.service.ApplicationStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationStatisticsAction extends BaseAction {

    @Autowired
    private ApplicationStatisticsService applicationStatisticsService;

    @RequestMapping("queryApplicationStatistics")
    public void queryApplicationStatistics() {
//        Map<String, Object> dataGridMap = new HashMap<String, Object>();
//        try {
//            HibernateStatistics hibernateStatistics = applicationStatisticsService.queryHibernateStatistics();
//
//            dataGridMap.put("hibernateStatistics", hibernateStatistics);
//
//            super.writeJson(dataGridMap);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }


    public ApplicationStatisticsService getApplicationStatisticsService() {
        return applicationStatisticsService;
    }

    public void setApplicationStatisticsService(ApplicationStatisticsService applicationStatisticsService) {
        this.applicationStatisticsService = applicationStatisticsService;
    }
}
