package com.echodrama.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BasePojo implements java.io.Serializable {
    @Id
    @GenericGenerator(name = "uuidGen", strategy = "uuid")
    @GeneratedValue(generator = "uuidGen")
    @Column(name = "ID", length = 32, nullable = false)
    protected String id;

    @Column(name = "CREATED_DATE")
    protected Date createdDate;

    @Column(name = "MODIFIED_DATE")
    protected Date modifedDate;

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

    public Date getModifedDate() {
        return modifedDate;
    }

    public void setModifedDate(Date modifedDate) {
        this.modifedDate = modifedDate;
    }

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (this.createdDate == null) {
            this.createdDate = new Date();
        }
        this.modifedDate = new Date();
    }

}
