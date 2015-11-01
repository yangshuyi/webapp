package com.echodrama.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.echodrama.dao.DepartmentDao;
import com.echodrama.dao.EmployeeDao;
import com.echodrama.dao.JobTitleDao;
import com.echodrama.model.Department;
import com.echodrama.model.Employee;
import com.echodrama.model.JobTitle;
import com.echodrama.model.PaginationQueryModel;
import com.echodrama.pojo.DepartmentPojo;
import com.echodrama.pojo.EmployeePojo;
import com.echodrama.pojo.JobTitlePojo;
import com.echodrama.service.CacheSupport;
import com.echodrama.service.CodeService;
import com.echodrama.service.EmployeeService;
import com.echodrama.utility.StringUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService, CacheSupport {
    private final static String[] XMEMCACHED_KEYS = new String[]{};

    @Autowired
    private CodeService codeService;

    @Autowired
    private JobTitleDao jobTitleDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public PaginationQueryModel generalQuery(PaginationQueryModel pQueryModel) throws Exception {
        PaginationQueryModel queryModel = employeeDao.generalQuery(pQueryModel);
        List<EmployeePojo> employeePojoList = queryModel.getRows();
        List<Employee> employeeList = new ArrayList<Employee>();
        for (EmployeePojo pojo : employeePojoList) {
            employeeList.add(Employee.fromPojo(pojo));
        }
        queryModel.setRows(employeeList);
        return queryModel;
    }

    @Override
    public List<Employee> list(Map<String, Object> paramMap) throws Exception {
        return null;
    }

    @Override
    public Employee loadEmployee(String id) throws Exception {
        EmployeePojo employeePojo = employeeDao.get(EmployeePojo.class, id);

        if (employeePojo == null) {
            throw new Exception("Could not find employee by id [" + id + "].");
        }

        return new Employee(employeePojo);
    }

    @Override
    public Employee initNewEmployee(String departmentId) throws Exception {
        Employee employee = new Employee();

        if (!StringUtils.isEmpty(departmentId)) {
            DepartmentPojo departmentPojo = departmentDao.get(DepartmentPojo.class, departmentId);
            if (departmentPojo == null) {
                throw new Exception("Could not find Department by Id [" + departmentId + "]");
            }
            employee.setDepartment(new Department(departmentPojo));
        }

        return employee;
    }

    @Override
    public void saveEmployee(Employee employee) throws Exception {
        EmployeePojo employeePojo;
        if (StringUtils.isEmpty(employee.getId())) {
            employeePojo = new EmployeePojo();
        } else {
            employeePojo = employeeDao.get(DepartmentPojo.class, employee.getId());
            if (employeePojo == null) {
                throw new Exception("Could not find Employee with id [" + employee.getId() + "]");
            }
        }

        employeePojo = employee.toPojo(employeePojo);

        Department department = employee.getDepartment();
        if (StringUtility.isValueChanged(department, employeePojo.getDepartment())) {
            if (department != null) {
                DepartmentPojo departmentPojo = departmentDao.get(DepartmentPojo.class, department.getId());
                if (departmentPojo == null) {
                    throw new Exception("Could not find Parent Department with department id [" + department.getId() + "]");
                }
                employeePojo.setDepartment(departmentPojo);
            } else {
                employeePojo.setDepartment(null);
            }
        }

        JobTitle jobTitle = employee.getJobTitle();
        if (StringUtility.isValueChanged(jobTitle, employeePojo.getJobTitle())) {
            if (jobTitle != null) {
                JobTitlePojo jobTitlePojo = jobTitleDao.get(JobTitlePojo.class, jobTitle.getId());
                if (jobTitlePojo == null) {
                    throw new Exception("Could not find JobTitle with id [" + jobTitle.getId() + "]");
                }
                employeePojo.setJobTitle(jobTitlePojo);
            } else {
                employeePojo.setJobTitle(null);
            }
        }

        validateBeforeSave(employeePojo);

        employeeDao.saveOrUpdate(employeePojo);

        clearCache(XMEMCACHED_KEYS);
    }

    private void validateBeforeSave(EmployeePojo employeePojo) throws Exception {
        if (StringUtils.isEmpty(employeePojo.getName())) {
            throw new Exception("Employee [" + employeePojo.getName() + "] should have the name property.");
        }

        if (StringUtils.isEmpty(employeePojo.getCode())) {
            throw new Exception("Employee [" + employeePojo.getName() + "] should have the code property.");
        }

        boolean conflicted = employeeDao.checkCodeConflicted(employeePojo.getId(), employeePojo.getCode());
        if (conflicted) {
            throw new Exception("Employee code [" + employeePojo.getCode() + "] is conflicted with others");
        }

        if (employeePojo.getDepartment() == null) {
            throw new Exception("Employee [" + employeePojo.getName() + "] should have the Department property.");
        }

        if (employeePojo.getJobTitle() == null) {
            throw new Exception("Employee [" + employeePojo.getName() + "] should have the JobTitle property.");
        }
    }

    @Override
    public void clearCache(String[] keys) throws Exception {
        //        for (String key : keys) {
        //            this.memcachedClient.delete(key);
        //        }
    }

}
