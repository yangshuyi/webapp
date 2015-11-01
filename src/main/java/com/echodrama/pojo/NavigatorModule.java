package com.echodrama.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "T_NAVIGATOR_MODULE", schema = "")
public class NavigatorModule extends BasePojo {

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", length = 200, nullable = true)
    private String description;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "navigatorModule")
    @OrderColumn(name = "POSITION")
    private List<NavigatorItemPojo> items;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<NavigatorItemPojo> getItems() {
        if (items == null) {
            items = new ArrayList<NavigatorItemPojo>();
        }
        return items;
    }

    public void setItems(List<NavigatorItemPojo> items) {
        this.items = items;
    }

    public void addItem(NavigatorItemPojo item) {
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
