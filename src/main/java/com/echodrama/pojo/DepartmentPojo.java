package com.echodrama.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "T_DEPARTMENT", schema = "")
public class DepartmentPojo extends BasePojo {

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "CODE", length = 200, nullable = false)
    private String code;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "parent", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<DepartmentPojo> children;

    @JSONField(serialize = false)
    @JoinColumn(name = "PARENT_ID")
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private DepartmentPojo parent;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @JSONField(serialize = false)
    //integer, string, character, date, timestamp, float, binary, serializable, object, blob
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "department", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<EmployeePojo> employees;

    @JoinColumn(name = "MANAGER_ID")
    @OneToOne
    private EmployeePojo manager;

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

    public Set<EmployeePojo> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeePojo> employees) {
        this.employees = employees;
    }

    public EmployeePojo getManager() {
        return manager;
    }

    public void setManager(EmployeePojo manager) {
        this.manager = manager;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<DepartmentPojo> getChildren() {
        return children;
    }

    public void setChildren(Set<DepartmentPojo> children) {
        this.children = children;
    }

    public DepartmentPojo getParent() {
        return parent;
    }

    public void setParent(DepartmentPojo parent) {
        this.parent = parent;
    }
}
