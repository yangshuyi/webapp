package com.echodrama.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "T_USER", schema = "")
public class UserPojo extends BasePojo {
    private static final long serialVersionUID = -3465649019385221478L;

    @Column(name = "unitId", length = 20, nullable = false)
    private String unitId;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "lastlogintime", nullable = false)
    private Date lastLoginTime;

    @Column(name = "DISABLED")
    private boolean disabled;

    @JoinColumn(name = "EMPLOYEE_ID", insertable = true, unique = true)
    private EmployeePojo employee;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    @JoinTable(name = "User_Role", schema = "",
//            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)},
//            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", nullable = false)}
//    )
//    private Set<Role> roles;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public EmployeePojo getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeePojo employee) {
        this.employee = employee;
    }
}
