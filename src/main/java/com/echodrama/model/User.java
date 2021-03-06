package com.echodrama.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.echodrama.pojo.UserPojo;
import com.echodrama.utility.StringUtility;

import java.util.Date;

public class User extends BaseModel {

    private String unitId;

    private Unit unit;

    private String name;

    @JSONField(serialize = false)
    private String password;

    private Date lastLoginTime;

    private boolean disabled;

    private Employee employee;

    public User() {
        super();
    }

    public User(UserPojo pojo) {
        super(pojo);

        if (pojo == null) {
            return;
        }

        this.unitId = pojo.getUnitId();
        this.name = pojo.getName();
        this.password = pojo.getPassword();
        this.lastLoginTime = pojo.getLastLoginTime();
        this.disabled = pojo.isDisabled();
    }

    public static User fromPojo(UserPojo pojo) {
        User user = new User(pojo);

        if (pojo.getEmployee() != null) {
            user.setEmployee(new Employee(pojo.getEmployee()));
        } else {
            user.setEmployee(null);
        }

        return user;
    }

    public UserPojo toPojo(UserPojo pojo) {
        super.toPojo(pojo);

        if (StringUtility.isValueChanged(unitId, pojo.getUnitId())) {
            pojo.setUnitId(unitId);
        }

        if (StringUtility.isValueChanged(name, pojo.getName())) {
            pojo.setName(name);
        }

        if (StringUtility.isValueChanged(password, pojo.getPassword())) {
            pojo.setPassword(password);
        }

        if (StringUtility.isValueChanged(lastLoginTime, pojo.getLastLoginTime())) {
            pojo.setLastLoginTime(lastLoginTime);
        }

        if (StringUtility.isValueChanged(disabled, pojo.isDisabled())) {
            pojo.setDisabled(disabled);
        }

        return pojo;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
