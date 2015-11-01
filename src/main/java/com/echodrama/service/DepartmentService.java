package com.echodrama.service;

import com.echodrama.model.Department;

public interface DepartmentService {

    public Department loadRootDepartment() throws Exception;

    public Department loadDepartment(String id) throws Exception;

    public Department loadDepartmentWithEmployees(String id) throws Exception;

    public Department initNewDepartment(String parentId) throws Exception;

    public void deleteDepartments(String[] ids) throws Exception;

    public void deleteDepartmentWithChildren(String id) throws Exception;

    public void saveDepartment(Department department) throws Exception;


}
