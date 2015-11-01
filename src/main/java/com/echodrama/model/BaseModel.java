package com.echodrama.model;

import com.echodrama.pojo.BasePojo;
import com.echodrama.utility.StringUtility;

import java.util.Date;

/**
 * Created by shuyi on 14-2-9.
 */
public class BaseModel implements java.io.Serializable {
    protected String id;

    protected Date createdDate;

    protected Date modifedDate;

    public BaseModel() {
        super();
    }

    public BaseModel(BasePojo pojo) {
        if (pojo == null) {
            return;
        }

        this.id = pojo.getId();
        this.createdDate = pojo.getCreatedDate();
        this.modifedDate = pojo.getModifedDate();
    }

    public BasePojo toPojo(BasePojo pojo) {
        if (StringUtility.isValueChanged(id, pojo.getId())) {
            pojo.setId(id);
        }

        return pojo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    //TODO
    public String getCreatedDateLocalized() {
        return "";
    }

    public Date getModifedDate() {
        return modifedDate;
    }

    public void setModifedDate(Date modifedDate) {
        this.modifedDate = modifedDate;
    }

    //TODO
    public String getModifiedDateLocalized() {
        return "";
    }

}
