package com.echodrama.service;

import com.echodrama.model.Employee;
import com.echodrama.model.PaginationQueryModel;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public PaginationQueryModel generalQuery(PaginationQueryModel queryModel) throws Exception;

    public List<Employee> list(Map<String, Object> paramMap) throws Exception;

    public Employee loadEmployee(String id) throws Exception;

    public Employee initNewEmployee(String departmentId) throws Exception;

    public void saveEmployee(Employee employee) throws Exception;
}
