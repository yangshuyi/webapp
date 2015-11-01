package com.echodrama.action;

import com.echodrama.form.DepartmentForm;
import com.echodrama.service.DepartmentService;
import com.echodrama.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DepartmentAction extends BaseAction {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    private DepartmentForm departmentForm = new DepartmentForm();

    @RequestMapping("queryDepartmentTreeGrid")
    public void queryDepartmentTreeGrid() throws Exception {
//        Map<String, Object> dataGridMap = new HashMap<String, Object>();
//        try {
//            Department rootDepartment = departmentService.loadRootDepartment();
//
//            dataGridMap.put("departmentTreeObj", rootDepartment);
//            super.writeJson(dataGridMap);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    @RequestMapping("queryDepartmentSimpleTree")
    public void queryDepartmentSimpleTree() throws Exception {
//        Map<String, Object> dataGridMap = new HashMap<String, Object>();
//        try {
//            Department rootDepartment = departmentService.loadRootDepartment();
//            Map<String, Object> departmentTreeObj = EasyUIUtility.buildEasyUITreeObj(rootDepartment);
//
//            dataGridMap.put("departmentTreeObj", departmentTreeObj);
//            super.writeJson(dataGridMap);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            super.writeJsonErrorMessage(ex);
//        }
    }

    @RequestMapping("loadDepartment")
    public void loadDepartment() throws Exception {
//        Map<String, Object> dataGridMap = new HashMap<String, Object>();
//
//        try {
//            Department department = null;
//            if(departmentForm.getId().equals("8a14c6e24428de84014428de9541001c")){
//                ThreadLocalUtil.setObject(ThreadLocalUtil.KEY_UNIT_ID, "01");
//            }else{
//                ThreadLocalUtil.setObject(ThreadLocalUtil.KEY_UNIT_ID, "02");
//            }
//
//            if (StringUtils.isEmpty(departmentForm.getId()) && !StringUtils.isEmpty(departmentForm.getParentId())) {
//                department = departmentService.initNewDepartment(departmentForm.getParentId());
//            } else  if (StringUtils.isEmpty(departmentForm.getId()) && StringUtils.isEmpty(departmentForm.getParentId())) {
//                department = departmentService.initNewDepartment(departmentForm.getParentId());
//            } else if (!StringUtils.isEmpty(departmentForm.getId())) {
//                department = departmentService.loadDepartmentWithEmployees(departmentForm.getId());
//            } else {
//                throw new Exception("Unhandle Action Condition");
//            }
//            dataGridMap.put("department", department);
//
//            dataGridMap.put("employees", department.getEmployees());
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

    @RequestMapping("saveDepartment")
    public void saveDepartment() {
//        Department department = null;
//        try {
//            if (StringUtils.isEmpty(departmentForm.getId())) {
//                department = new Department();
//            } else {
//                department = departmentService.loadDepartment(departmentForm.getId());
//                if (department == null) {
//                    throw new Exception("could not find Department by id [" + departmentForm.getId() + "]");
//                }
//            }
//
//            Department parentDepartment = null;
//            if (!StringUtils.isEmpty(departmentForm.getParentId())) {
//                parentDepartment = new Department();
//                parentDepartment.setId(departmentForm.getParentId());
//            }
//
//            Employee manager = null;
//            if (!StringUtils.isEmpty(departmentForm.getManagerId())) {
//                manager = new Employee();
//                manager.setId(departmentForm.getManagerId());
//            }
//
//            //copy properties
//            department.setName(departmentForm.getName());
//            department.setCode(departmentForm.getCode());
//            department.setParent(parentDepartment);
//            department.setManager(manager);
//            department.setDescription(departmentForm.getDescription());
//
//            departmentService.saveDepartment(department);
//
//            super.writeJson(department);
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
}
