package com.echodrama.dao;

import com.echodrama.model.PaginationQueryModel;
import com.echodrama.pojo.JobTitlePojo;

public interface JobTitleDao extends BaseDao<JobTitlePojo> {

    public PaginationQueryModel generalQuery(PaginationQueryModel<JobTitlePojo> queryModel) throws Exception;

}
