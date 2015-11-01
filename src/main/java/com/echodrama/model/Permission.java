package com.echodrama.model;

import com.echodrama.pojo.BasePojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "T_PERMISSION", schema = "")
public class Permission extends BasePojo {

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "description", length = 200, nullable = true)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
