package com.echodrama.pojo;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CodePojo extends BasePojo {

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "CODE", length = 200, nullable = false)
    private String code;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
