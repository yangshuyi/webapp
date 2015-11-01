package com.echodrama.dao.impl;

import com.echodrama.dao.EmployeeDao;
import com.echodrama.model.PaginationQueryModel;
import com.echodrama.pojo.EmployeePojo;
import com.echodrama.utility.Constants;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDaoImpl<EmployeePojo> implements EmployeeDao {

    @Override
    public PaginationQueryModel generalQuery(PaginationQueryModel<EmployeePojo> queryModel) throws Exception {
        String queryHql = "FROM EmployeePojo WHERE 1=1 ";
        String countHql = "SELECT COUNT(id) FROM EmployeePojo WHERE 1=1 ";

        List<EmployeePojo> employeeList = null;

        String paramHQL = queryModel.buildQueryHQL();
        queryHql += paramHQL;
        countHql += paramHQL;

        if (queryModel.getSort() != null) {
            queryHql += " ORDER BY " + queryModel.getSort() + " " + queryModel.getOrder();
        }

        if (queryModel.getMaxResults() != Constants.INVALID_NUMBER) {

            int recordCount = this.countByHql(countHql, queryModel.getParamMap().values());
            queryModel.setTotal(recordCount);

            employeeList = this.queryByHql(queryHql, queryModel.getParamMap().values(), queryModel.getFirstRecord(), queryModel.getMaxResults());
        } else {
            employeeList = this.queryByHql(queryHql, queryModel.getParamMap().values());
        }

        queryModel.setRows(employeeList);
        return queryModel;
    }

    public boolean checkCodeConflicted(String id, String code) throws Exception {
        final String HQL = "FROM EmployeePojo as mpe WHERE mpe.code = :code AND mpe.id != :id";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", code == null ? "" : code);
        paramMap.put("id", id == null ? "" : id);

        List<EmployeePojo> employeeList = this.queryByHql(HQL, paramMap);
        return employeeList.size() != 0;
    }
}
