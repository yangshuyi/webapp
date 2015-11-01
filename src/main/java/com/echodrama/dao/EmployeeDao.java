package com.echodrama.dao;

import com.echodrama.model.PaginationQueryModel;
import com.echodrama.pojo.EmployeePojo;

public interface EmployeeDao extends BaseDao<EmployeePojo> {

    public PaginationQueryModel generalQuery(PaginationQueryModel<EmployeePojo> queryModel) throws Exception;

    public boolean checkCodeConflicted(String id, String code) throws Exception;
}
