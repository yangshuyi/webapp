package com.echodrama.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.echodrama.pojo.DepartmentPojo;
import com.echodrama.utility.StringUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Department extends BaseModel implements TreeInterface {
    private String name;

    private String code;

    private List<Department> children;

    private Department parent;

    private String description;

    @JSONField(serialize = false)
    private List<Employee> employees = new ArrayList<Employee>();

    private int employeeNum;

    private Employee manager;

    public Department() {
        super();
    }

    public Department(DepartmentPojo pojo) {
        super(pojo);

        if (pojo == null) {
            return;
        }

        this.name = pojo.getName();
        this.code = pojo.getCode();
        this.employeeNum = pojo.getEmployees().size();
        this.description = pojo.getDescription();
    }

    public static Department fromPojo(DepartmentPojo pojo, boolean withChildren) {
        Department department = new Department(pojo);

        if (pojo.getManager() != null) {
            department.setManager(new Employee(pojo.getManager()));
        } else {
            department.setManager(null);
        }

        if (pojo.getParent() != null) {
            department.setParent(new Department(pojo.getParent()));
        } else {
            department.setParent(null);
        }

        if (withChildren) {
            List<Department> children = buildSubDepartment(pojo);
            department.setChildren(children);
        }

        return department;
    }

    private static List<Department> buildSubDepartment(DepartmentPojo pojo) {
        if (pojo.getChildren() != null) {
            List<Department> children = new ArrayList<Department>();
            for (DepartmentPojo childDepartment : pojo.getChildren()) {
                children.add(Department.fromPojo(childDepartment, true));
            }

            Collections.sort(children, new Comparator<Department>() {
                @Override
                public int compare(Department o1, Department o2) {
                    return o1.getCode().compareTo(o2.getCode());
                }
            });
            return children;
        }
        return null;

    }

    public DepartmentPojo toPojo(DepartmentPojo pojo) {
        super.toPojo(pojo);

        if (StringUtility.isValueChanged(name, pojo.getName())) {
            pojo.setName(name);
        }

        if (StringUtility.isValueChanged(code, pojo.getCode())) {
            pojo.setCode(code);
        }

        if (StringUtility.isValueChanged(description, pojo.getDescription())) {
            pojo.setDescription(description);
        }

        return pojo;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getEmployeeNum() {
        if (this.employees == null) {
            return this.employeeNum;
        }
        return this.employees.size();
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }
}
