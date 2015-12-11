package com.echodrama.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.echodrama.enumtype.TopicTypeEnum;
import com.echodrama.pojo.BasePojo;
import com.echodrama.pojo.UserPojo;
import com.echodrama.utility.StringUtility;

public class Topic extends BaseModel {
    private String subject;
    private String picPath;
    private String content;
    private TopicTypeEnum type;


    public Topic() {
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
