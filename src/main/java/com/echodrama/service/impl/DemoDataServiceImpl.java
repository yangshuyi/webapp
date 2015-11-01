package com.echodrama.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.echodrama.dao.DepartmentDao;
import com.echodrama.dao.EmployeeDao;
import com.echodrama.dao.JobTitleDao;
import com.echodrama.dao.NavigatorItemDao;
import com.echodrama.dao.NavigatorModuleDao;
import com.echodrama.dao.UserDao;
import com.echodrama.pojo.DepartmentPojo;
import com.echodrama.pojo.EmployeePojo;
import com.echodrama.pojo.JobTitlePojo;
import com.echodrama.pojo.NavigatorItemPojo;
import com.echodrama.pojo.NavigatorModule;
import com.echodrama.service.DemoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("demoDataService")
public class DemoDataServiceImpl implements DemoDataService {

    @Autowired
    private NavigatorModuleDao navigatorModuleDao;

    @Autowired
    private NavigatorItemDao navigatorItemDao;

    @Autowired
    private JobTitleDao jobTitleDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserDao userDao;

    @Override
    public void createDemoData() throws Exception {

        createNavigatorModuleItems();

        List<JobTitlePojo> jobTitleList = createJobTitle();

        //create department data
        List<DepartmentPojo> departmentList = createDepartment();

        List<EmployeePojo> employeeList = createEmployee(jobTitleList, departmentList);


    }

    public void createNavigatorModuleItems() {
        for (int moduleIdx = 0; moduleIdx < 3; moduleIdx++) {
            NavigatorModule module = new NavigatorModule();
            module.setName("Module " + moduleIdx);
            module.setDescription("Description");
            navigatorModuleDao.save(module);

            for (int itemIdx = 0; itemIdx < 5; itemIdx++) {
                NavigatorItemPojo navigatorItem = new NavigatorItemPojo();
                navigatorItem.setName("Item " + itemIdx);
                navigatorItem.setActionUrl("/jsp/organization/applicationStatistics.jsp");
                navigatorItem.setImageUrl("/image/user/user.png");

                module.addItem(navigatorItem);
                navigatorItemDao.save(navigatorItem);
            }


        }

    }

    private List<JobTitlePojo> createJobTitle() {
        List<JobTitlePojo> jobTitleList = new ArrayList<JobTitlePojo>();

        for (int i = 0; i < 10; i++) {
            String idxStr = String.format("%04d", i);

            JobTitlePojo jobTitle = new JobTitlePojo();

            jobTitle.setName("Job Title " + idxStr);
            jobTitle.setCode(idxStr);

            jobTitleDao.save(jobTitle);

            jobTitleList.add(jobTitle);
        }

        return jobTitleList;
    }

    private List<DepartmentPojo> createDepartment() throws Exception {
        DepartmentPojo department = departmentDao.loadRootDepartment();
        if (department != null) {
            departmentDao.delete(department);
        }

        List<DepartmentPojo> departmentList = new ArrayList<DepartmentPojo>();

        DepartmentPojo department01 = new DepartmentPojo();

        department01.setName("董事会");
        department01.setCode("01");
        department01.setManager(null);
        department01.setEmployees(new HashSet<EmployeePojo>());
        department01.setCreatedDate(new Date());
        departmentDao.save(department01);
        departmentList.add(department01);

        DepartmentPojo department0101 = new DepartmentPojo();
        department0101.setName("总经理");
        department0101.setCode("01.01");
        department0101.setManager(null);
        department0101.setParent(department01);
        department0101.setEmployees(new HashSet<EmployeePojo>());
        department0101.setCreatedDate(new Date());
        departmentDao.save(department0101);
        departmentList.add(department0101);

        DepartmentPojo department010101 = new DepartmentPojo();
        department010101.setName("财务部");
        department010101.setCode("01.01.01");
        department010101.setManager(null);
        department010101.setParent(department0101);
        department010101.setEmployees(new HashSet<EmployeePojo>());
        department010101.setCreatedDate(new Date());
        departmentDao.save(department010101);
        departmentList.add(department010101);

        DepartmentPojo department010102 = new DepartmentPojo();
        department010102.setName("销售部");
        department010102.setCode("01.01.02");
        department010102.setManager(null);
        department010102.setParent(department0101);
        department010102.setEmployees(new HashSet<EmployeePojo>());
        department010102.setCreatedDate(new Date());
        departmentDao.save(department010102);
        departmentList.add(department010102);


        DepartmentPojo department010104 = new DepartmentPojo();
        department010104.setName("采购部");
        department010104.setCode("01.01.04");
        department010104.setManager(null);
        department010104.setParent(department0101);
        department010104.setEmployees(new HashSet<EmployeePojo>());
        department010104.setCreatedDate(new Date());
        departmentDao.save(department010104);
        departmentList.add(department010104);

        DepartmentPojo department010105 = new DepartmentPojo();
        department010105.setName("生产部");
        department010105.setCode("01.01.05");
        department010105.setManager(null);
        department010105.setParent(department0101);
        department010105.setEmployees(new HashSet<EmployeePojo>());
        department010105.setCreatedDate(new Date());
        departmentDao.save(department010105);
        departmentList.add(department010105);

        DepartmentPojo department010106 = new DepartmentPojo();
        department010106.setName("研发部");
        department010106.setCode("01.01.06");
        department010106.setManager(null);
        department010106.setParent(department0101);
        department010106.setEmployees(new HashSet<EmployeePojo>());
        department010106.setCreatedDate(new Date());
        departmentDao.save(department010106);
        departmentList.add(department010106);

        DepartmentPojo department01010601 = new DepartmentPojo();
        department01010601.setName("研发一部");
        department01010601.setCode("01.01.06.01");
        department01010601.setManager(null);
        department01010601.setParent(department010106);
        department01010601.setEmployees(new HashSet<EmployeePojo>());
        department01010601.setCreatedDate(new Date());
        departmentDao.save(department01010601);
        departmentList.add(department01010601);

        DepartmentPojo department01010602 = new DepartmentPojo();
        department01010602.setName("研发二部");
        department01010602.setCode("01.01.06.02");
        department01010602.setManager(null);
        department01010602.setParent(department010106);
        department01010602.setEmployees(new HashSet<EmployeePojo>());
        department01010602.setCreatedDate(new Date());
        departmentDao.save(department01010602);
        departmentList.add(department01010602);

        DepartmentPojo department01010603 = new DepartmentPojo();
        department01010603.setName("研发三部");
        department01010603.setCode("01.01.06.03");
        department01010603.setManager(null);
        department01010603.setParent(department010106);
        department01010603.setEmployees(new HashSet<EmployeePojo>());
        department01010603.setCreatedDate(new Date());
        departmentDao.save(department01010603);
        departmentList.add(department01010603);

        DepartmentPojo department010107 = new DepartmentPojo();
        department010107.setName("测试部");
        department010107.setCode("01.01.07");
        department010107.setManager(null);
        department010107.setParent(department0101);
        department010107.setEmployees(new HashSet<EmployeePojo>());
        department010107.setCreatedDate(new Date());
        departmentDao.save(department010107);
        departmentList.add(department010107);

        return departmentList;
    }


    private List<EmployeePojo> createEmployee(List<JobTitlePojo> jobTitleList, List<DepartmentPojo> departmentList) {
        List<EmployeePojo> employeeList = new ArrayList<EmployeePojo>();

        for (int i = 0; i < 110; i++) {
            String idxStr = String.format("%04d", i);

            EmployeePojo employee = new EmployeePojo();

            employee.setName("EmployeePojo " + idxStr);
            employee.setCode(idxStr);
            employee.setMailAddress("Employee_" + idxStr + "@google.com");
            employee.setMobileNumber("021-12345678");
            employee.setJobTitle(jobTitleList.get(i % 10));
            employee.setDepartment(departmentList.get(i % 10));

            employeeDao.save(employee);

            employeeList.add(employee);
        }

        return employeeList;
    }

    public NavigatorModuleDao getNavigatorModuleDao() {
        return navigatorModuleDao;
    }

    public void setNavigatorModuleDao(NavigatorModuleDao navigatorModuleDao) {
        this.navigatorModuleDao = navigatorModuleDao;
    }

    public NavigatorItemDao getNavigatorItemDao() {
        return navigatorItemDao;
    }

    public void setNavigatorItemDao(NavigatorItemDao navigatorItemDao) {
        this.navigatorItemDao = navigatorItemDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public JobTitleDao getJobTitleDao() {
        return jobTitleDao;
    }

    public void setJobTitleDao(JobTitleDao jobTitleDao) {
        this.jobTitleDao = jobTitleDao;
    }

    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
