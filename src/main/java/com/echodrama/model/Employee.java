package com.echodrama.model;


import com.echodrama.pojo.EmployeePojo;
import com.echodrama.utility.StringUtility;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Employee")
public class Employee extends BaseModel {
    private String name;

    private String code;

    private JobTitle jobTitle;

    private String mailAddress;

    private String mobileNumber;

    private Department department;

    private boolean disabled;

    public Employee() {
        super();
    }

    public Employee(EmployeePojo pojo) {
        super(pojo);

        if (pojo == null) {
            return;
        }

        this.name = pojo.getName();
        this.code = pojo.getCode();
        this.mailAddress = pojo.getMailAddress();
        this.mobileNumber = pojo.getMobileNumber();
        this.disabled = pojo.isDisabled();
    }

    public static Employee fromPojo(EmployeePojo pojo) {
        Employee employee = new Employee(pojo);

        if (pojo.getJobTitle() != null) {
            employee.setJobTitle(new JobTitle(pojo.getJobTitle()));
        } else {
            employee.setJobTitle(null);
        }

        if (pojo.getDepartment() != null) {
            employee.setDepartment(new Department(pojo.getDepartment()));
        } else {
            employee.setDepartment(null);
        }
        return employee;
    }

    public EmployeePojo toPojo(EmployeePojo pojo) {
        super.toPojo(pojo);

        if (StringUtility.isValueChanged(name, pojo.getName())) {
            pojo.setName(name);
        }

        if (StringUtility.isValueChanged(code, pojo.getCode())) {
            pojo.setCode(code);
        }

        if (StringUtility.isValueChanged(mailAddress, pojo.getMailAddress())) {
            pojo.setMailAddress(mailAddress);
        }

        if (StringUtility.isValueChanged(mobileNumber, pojo.getMobileNumber())) {
            pojo.setMobileNumber(mobileNumber);
        }

        return pojo;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    @XmlElement(name = "JobTitleName")
    public String getJobTitleName() {
        return jobTitle == null ? "" : jobTitle.getName();
    }

    @XmlElement(name = "MailAddress")
    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @XmlElement(name = "MobileNumber")
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @XmlElement(name = "DepartmentName")
    public String getDepartmentName() {
        return department == null ? "" : department.getName();
    }

    public boolean isDisabled() {
        return disabled;
    }

    @XmlElement(name = "Disabled")
    public String getDisabled() {
        return disabled ? "已禁用" : "已启用";
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
