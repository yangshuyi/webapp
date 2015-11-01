package com.echodrama.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.echodrama.dao.DepartmentDao;
import com.echodrama.dao.EmployeeDao;
import com.echodrama.model.Department;
import com.echodrama.model.Employee;
import com.echodrama.pojo.DepartmentPojo;
import com.echodrama.pojo.EmployeePojo;
import com.echodrama.service.CacheSupport;
import com.echodrama.service.DepartmentService;
import com.echodrama.utility.Constants;
import com.echodrama.utility.StringUtility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService, CacheSupport {
    private final static String[] XMEMCACHED_KEYS = new String[]{Constants.XMEMCACHED_KEY_DEARTPMENT_ROOT};

    //    @Autowired
    //    private MemcachedClient memcachedClient;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Department loadRootDepartment() throws Exception {
        Department rootDepartment = null;

        DepartmentPojo departmentPojo = departmentDao.loadRootDepartment();
        if (departmentPojo == null) {
            throw new Exception("Could not find Root departmentPojo.");
        }

        rootDepartment = Department.fromPojo(departmentPojo, true);

        return rootDepartment;
    }


    @Override
    public Department initNewDepartment(String parentId) throws Exception {
        Department department = new Department();
        department.setName("New DepartmentPojo");

        DepartmentPojo parentDepartment = departmentDao.get(DepartmentPojo.class, parentId);
        if (parentDepartment == null) {
            throw new Exception("Could not find Department by Id [" + parentId + "]");
        }

        department.setParent(new Department(parentDepartment));

        return department;
    }

    @Override
    public Department loadDepartment(String id) throws Exception {
        DepartmentPojo departmentPojo = departmentDao.get(DepartmentPojo.class, id);
        if (departmentPojo == null) {
            throw new Exception("Could not find Department by Id [" + id + "]");
        }

        Department department = Department.fromPojo(departmentPojo, false);

        return department;
    }

    @Override
    public Department loadDepartmentWithEmployees(String id) throws Exception {
        DepartmentPojo departmentPojo = departmentDao.get(DepartmentPojo.class, id);
        if (departmentPojo == null) {
            throw new Exception("Could not find Department by Id [" + id + "]");
        }

        Department department = Department.fromPojo(departmentPojo, false);
        List<Employee> employeeList = new ArrayList<Employee>();
        for (EmployeePojo employeePojo : departmentPojo.getEmployees()) {
            employeeList.add(Employee.fromPojo(employeePojo));
        }
        department.setEmployees(employeeList);
        return department;
    }

    @Override
    public void deleteDepartments(String[] ids) throws Exception {
        String hql = "FROM DepartmentPojo as dpt WHERE dpt.id IN (:ids)";

//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("ids", ids);
//        List<DepartmentPojo> departmentList = departmentDao.queryByHql(hql, paramMap);

        clearCache(XMEMCACHED_KEYS);
    }


    public void deleteDepartmentWithChildren(String id) throws Exception {
        DepartmentPojo departmentPojo = departmentDao.get(DepartmentPojo.class, id);
        if (departmentPojo == null) {
            throw new Exception("Could not find Department with id [" + id + "]");
        }

        this.departmentDao.delete(departmentPojo);

        clearCache(XMEMCACHED_KEYS);
    }

    @Override
    public void saveDepartment(Department department) throws Exception {
        DepartmentPojo departmentPojo;
        if (StringUtils.isEmpty(department.getId())) {
            departmentPojo = new DepartmentPojo();
        } else {
            departmentPojo = departmentDao.get(DepartmentPojo.class, department.getId());
            if (departmentPojo == null) {
                throw new Exception("Could not find Department with id [" + department.getId() + "]");
            }
        }

        departmentPojo = department.toPojo(departmentPojo);

        Department parentDepartment = department.getParent();
        if (StringUtility.isValueChanged(parentDepartment, departmentPojo.getParent())) {
            if (parentDepartment != null) {
                DepartmentPojo parentDepartmentPojo = departmentDao.get(DepartmentPojo.class, parentDepartment.getId());
                if (parentDepartmentPojo == null) {
                    throw new Exception("Could not find Parent Department with departmentPojo id [" + parentDepartment.getId() + "]");
                }
                departmentPojo.setParent(parentDepartmentPojo);
            } else {
                departmentPojo.setParent(null);
            }
        }

        Employee manager = department.getManager();
        if (StringUtility.isValueChanged(manager, departmentPojo.getManager())) {
            if (manager != null) {
                EmployeePojo managerPojo = employeeDao.get(EmployeePojo.class, manager.getId());
                if (managerPojo == null) {
                    throw new Exception("Could not find Department Manager with employee id [" + manager.getId() + "]");
                }
                departmentPojo.setManager(managerPojo);
            } else {
                departmentPojo.setManager(null);
            }
        }

        validateBeforeSave(departmentPojo);

        departmentDao.saveOrUpdate(departmentPojo);

        clearCache(XMEMCACHED_KEYS);
    }

    private void validateBeforeSave(DepartmentPojo departmentPojo) throws Exception {
        if (StringUtils.isEmpty(departmentPojo.getName())) {
            throw new Exception("Department [" + departmentPojo.getName() + "] should have the name property.");
        }

        if (StringUtils.isEmpty(departmentPojo.getCode())) {
            throw new Exception("Department [" + departmentPojo.getName() + "] should have the code property.");
        }

        boolean conflicted = departmentDao.checkCodeConflicted(departmentPojo.getId(), departmentPojo.getCode());
        if (conflicted) {
            throw new Exception("Department code [" + departmentPojo.getCode() + "] is conflicted with others");
        }

        Department rootDepartment = loadRootDepartment();
        if (rootDepartment != null && departmentPojo.getParent() == null) {
            if (!rootDepartment.getId().equals(departmentPojo.getId())) {
                throw new Exception("Only one roof Department is supported.");
            }
        }

        boolean isCircularRef = false;
        DepartmentPojo parent = departmentPojo.getParent();
        while (true) {
            if (parent == null) {
                break;
            }
            if (parent.getId().equals(departmentPojo.getId())) {
                isCircularRef = true;
                break;
            }

            parent = parent.getParent();
        }

        if (isCircularRef) {
            throw new Exception("Department can not be circular referenced.");
        }
    }

    @Override
    public void clearCache(String[] keys) throws Exception {
        for (String key : keys) {
            //            this.memcachedClient.delete(key);
        }
    }
}
