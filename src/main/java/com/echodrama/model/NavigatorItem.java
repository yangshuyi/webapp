package com.echodrama.model;

import com.alibaba.fastjson.annotation.JSONField;

public class NavigatorItem extends BaseModel {

    private String name;

    private String description;

    private String imageUrl;

    private String actionUrl;

    @JSONField(serialize = false)
    private NavigatorModule navigatorModule;

    private int position;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NavigatorModule getNavigatorModule() {
        return navigatorModule;
    }

    public void setNavigatorModule(NavigatorModule navigatorModule) {
        this.navigatorModule = navigatorModule;
    }
}
