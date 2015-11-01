package com.echodrama.model;

import com.echodrama.pojo.UnitPojo;
import com.echodrama.utility.StringUtility;

public class Unit extends BaseModel {

    private String unitId;
    private String name;
    private String code;

    private boolean disabled;

    public Unit() {
        super();
    }

    public Unit(UnitPojo pojo) {
        super(pojo);

        if (pojo == null) {
            return;
        }

        this.name = pojo.getName();
        this.code = pojo.getCode();
        this.disabled = pojo.isDisabled();
    }

    public static Unit fromPojo(UnitPojo pojo) {
        Unit unit = new Unit(pojo);

        return unit;
    }

    public UnitPojo toPojo(UnitPojo pojo) {
        super.toPojo(pojo);

        if (StringUtility.isValueChanged(name, pojo.getName())) {
            pojo.setName(name);
        }

        if (StringUtility.isValueChanged(code, pojo.getCode())) {
            pojo.setCode(code);
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

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }


}
