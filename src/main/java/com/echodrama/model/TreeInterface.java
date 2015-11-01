package com.echodrama.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/10/14
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TreeInterface {
    public String getId();

    public String getCode();

    public String getName();

    public List<? extends TreeInterface> getChildren();

    public TreeInterface getParent();
}
