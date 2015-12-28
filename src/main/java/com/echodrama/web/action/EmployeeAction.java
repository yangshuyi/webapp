package com.echodrama.web.action;

import com.echodrama.web.form.EmployeeForm;
import com.echodrama.service.CodeService;
import com.echodrama.service.DepartmentService;
import com.echodrama.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EmployeeAction extends BaseAction {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CodeService codeService;

    private EmployeeForm employeeForm = new EmployeeForm();

    private HttpServletRequest request;

    @RequestMapping("queryEmployeeDataGrid")
    public void queryEmployeeDataGrid() {
//        Map<String, Object> dataGridMap = new HashMap<String, Object>();
//        try {
//            PaginationQueryModel queryModel = employeeForm.toQueryModel();
//            queryModel = employeeService.generalQuery(queryModel);
//
//            dataGridMap.put("employeeDataGrid", queryModel);
//
//            super.writeJson(dataGridMap);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    @RequestMapping("loadEmployee")
    public void loadEmployee() {
//        Map<String, Object> dataGridMap = new HashMap<String, Object>();
//
//        try {
//            Employee employee = null;
//
//            if (StringUtils.isEmpty(employeeForm.getId()) && !StringUtils.isEmpty(employeeForm.getDepartmentId())) {
//                employee = employeeService.initNewEmployee(employeeForm.getDepartmentId());
//            } else  if (StringUtils.isEmpty(employeeForm.getId()) && StringUtils.isEmpty(employeeForm.getDepartmentId())) {
//                employee = employeeService.initNewEmployee(null);
//            } else if (!StringUtils.isEmpty(employeeForm.getId())) {
//                employee = employeeService.loadEmployee(employeeForm.getId());
//            } else {
//                throw new Exception("Unhandle Action Condition");
//            }
//            dataGridMap.put("employee", employee);
//
//            dataGridMap.put("jobTitles", codeService.listByType(CodeEnum.JobTitle));
//
//            Department rootDepartment = departmentService.loadRootDepartment();
//            dataGridMap.put("departmentTreeObj", EasyUIUtility.buildEasyUITreeObj(rootDepartment));
//
//            super.writeJson(dataGridMap);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    @RequestMapping("saveEmployee")
    public void saveDepartment() {
//        Employee employee = null;
//        try {
//            if (StringUtils.isEmpty(employeeForm.getId())) {
//                employee = new Employee();
//            } else {
//                employee = employeeService.loadEmployee(employeeForm.getId());
//            }
//
//             Department department = null;
//            if (!StringUtils.isEmpty(employeeForm.getDepartmentId())) {
//                department = new Department();
//                department.setId(employeeForm.getDepartmentId());
//            }
//
//             JobTitle jobTitle = null;
//            if (!StringUtils.isEmpty(employeeForm.getJobTitleId())) {
//                jobTitle = new JobTitle();
//                jobTitle.setId(employeeForm.getJobTitleId());
//            }
//
//            //copy properties
//            employee.setName(employeeForm.getName());
//            employee.setCode(employeeForm.getCode());
//            employee.setDepartment(department);
//            employee.setJobTitle(jobTitle);
//            employee.setMailAddress(employeeForm.getMailAddress());
//            employee.setMobileNumber(employeeForm.getMobileNumber());
//
//            employeeService.saveEmployee(employee);
//
//            super.writeJson(employee);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public CodeService getCodeService() {
        return codeService;
    }

    public void setCodeService(CodeService codeService) {
        this.codeService = codeService;
    }

}
