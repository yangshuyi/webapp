package com.echodrama.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "T_EMPLOYEE", schema = "")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmployeePojo extends BasePojo {
    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "CODE", length = 200, nullable = false)
    private String code;

    @JoinColumn(name = "JOB_TITLE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private JobTitlePojo jobTitle;

    @Column(name = "MAIL_ADDRESS", length = 200)
    private String mailAddress;

    @Column(name = "MOBILE_NUMBER", length = 200)
    private String mobileNumber;

    @JSONField(serialize = false)
    @JoinColumn(name = "DEPARTMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private DepartmentPojo department;

    @Column(name = "DISABLED")
    private boolean disabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DepartmentPojo getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentPojo department) {
        this.department = department;
    }

    public JobTitlePojo getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitlePojo jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public String getDisabled() {
        return disabled ? "已禁用" : "已启用";
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
