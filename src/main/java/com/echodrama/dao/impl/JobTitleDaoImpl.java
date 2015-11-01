package com.echodrama.dao.impl;

import com.echodrama.dao.JobTitleDao;
import com.echodrama.model.PaginationQueryModel;
import com.echodrama.pojo.JobTitlePojo;
import com.echodrama.utility.Constants;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jobTitleDao")
public class JobTitleDaoImpl extends BaseDaoImpl<JobTitlePojo> implements JobTitleDao {

    @Override
    public PaginationQueryModel generalQuery(PaginationQueryModel<JobTitlePojo> queryModel) throws Exception {
        String queryHql = "FROM JobTitlePojo WHERE 1=1 ";
        String countHql = "SELECT COUNT(id) FROM JobTitlePojo WHERE 1=1 ";

        List<JobTitlePojo> jobTitlePojoList = null;

        String paramHQL = queryModel.buildQueryHQL();
        queryHql += paramHQL;
        countHql += paramHQL;

        if (queryModel.getSort() != null) {
            queryHql += " ORDER BY " + queryModel.getSort() + " " + queryModel.getOrder();
        }

        if (queryModel.getMaxResults() != Constants.INVALID_NUMBER) {
            int recordCount = this.countByHql(countHql, queryModel.getParamMap().values());
            queryModel.setTotal(recordCount);

            jobTitlePojoList = this.queryByHql(queryHql, queryModel.getParamMap().values(), queryModel.getFirstRecord(), queryModel.getMaxResults());
        } else {
            jobTitlePojoList = this.queryByHql(queryHql, queryModel.getParamMap().values());
        }

        queryModel.setRows(jobTitlePojoList);
        return queryModel;
    }
}
