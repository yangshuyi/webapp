package com.echodrama.model;

import java.util.ArrayList;
import java.util.List;

public class NavigatorModule extends BaseModel {

    private String name;

    private String description;

    private List<NavigatorItem> items;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<NavigatorItem> getItems() {
        if (items == null) {
            items = new ArrayList<NavigatorItem>();
        }
        return items;
    }

    public void setItems(List<NavigatorItem> items) {
        this.items = items;
    }

    public void addItem(NavigatorItem item) {
        if (item == null)
            return;

        this.getItems().add(item);
        item.setNavigatorModule(this);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
