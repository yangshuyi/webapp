package com.echodrama.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.echodrama.enumtype.TopicTypeEnum;
import com.echodrama.model.BaseModel;


@Entity
@Table(name = "T_TOPIC", schema = "")
public class TopicPojo extends BasePojo {
    private static final long serialVersionUID = -3465649019385221478L;

    @Column(name = "subject", length = 20, nullable = false)
    private String subject;


    @Column(name = "picPath", length = 100, nullable = false)
    private String picPath;

    @Column(name = "content", length = 100, nullable = false)
    private String content;

    @Column(name = "type", length = 100, nullable = false)
    private TopicTypeEnum type;


    public TopicPojo() {
        super();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TopicTypeEnum getType() {
        return type;
    }

    public void setType(TopicTypeEnum type) {
        this.type = type;
    }
}
