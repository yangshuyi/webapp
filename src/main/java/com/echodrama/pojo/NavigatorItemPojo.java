package com.echodrama.pojo;

import javax.persistence.*;


@Entity
@Table(name = "T_NAVIGATOR_ITEM", schema = "")
public class NavigatorItemPojo extends BasePojo {

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 200)
    private String description;

    @Column(name = "IMAGE_URL", length = 200)
    private String imageUrl;

    @Column(name = "ACTION_URL", length = 200)
    private String actionUrl;

    @ManyToOne()
    @JoinColumn(name = "NAVIGATOR_MODULE_ID")
    private NavigatorModule navigatorModule;

    @Column(name = "POSITION")
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
