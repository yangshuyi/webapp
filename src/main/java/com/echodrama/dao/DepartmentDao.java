package com.echodrama.dao;

import com.echodrama.pojo.DepartmentPojo;

public interface DepartmentDao extends BaseDao<DepartmentPojo> {

    public DepartmentPojo loadRootDepartment() throws Exception;

    public boolean checkCodeConflicted(String id, String code) throws Exception;
}
