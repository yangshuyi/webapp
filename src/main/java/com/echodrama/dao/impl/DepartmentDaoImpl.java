package com.echodrama.dao.impl;

import com.echodrama.dao.DepartmentDao;
import com.echodrama.pojo.DepartmentPojo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<DepartmentPojo> implements DepartmentDao {

    public DepartmentPojo loadRootDepartment() throws Exception {
        final String HQL = "from DepartmentPojo d where d.parent IS NULL";

        List<DepartmentPojo> departmentList = this.queryByHql(HQL, new HashMap<String, Object>());
        if (departmentList.size() == 1) {
            return departmentList.get(0);
        } else {
            return null;
        }
    }

    public boolean checkCodeConflicted(String id, String code) throws Exception {
        final String HQL = "FROM DepartmentPojo as dpt WHERE dpt.code = :code AND dpt.id != :id";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("code", code == null ? "" : code);
        paramMap.put("id", id == null ? "" : id);

        List<DepartmentPojo> departmentList = this.queryByHql(HQL, paramMap);
        return departmentList.size() != 0;
    }

    @Override
    public void delete(DepartmentPojo department) {
        Set<DepartmentPojo> childrenDepartments = department.getChildren();
        for (DepartmentPojo childrenDepartment : childrenDepartments) {
            this.delete(childrenDepartment);
        }
        this.delete(department);
    }
}
