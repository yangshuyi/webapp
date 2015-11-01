package com.echodrama.utility;

import com.echodrama.model.TreeInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyUIUtility {
    public static Map<String, Object> buildEasyUITreeObj(TreeInterface treeModel) {
        Map<String, Object> departmentTreeObj = new HashMap<String, Object>();
        departmentTreeObj.put("id", treeModel.getId());
        departmentTreeObj.put("text", treeModel.getName());
        departmentTreeObj.put("code", treeModel.getCode());
        departmentTreeObj.put("state", true);

        if (treeModel.getChildren() != null) {
            List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
            for (TreeInterface childTreeModel : treeModel.getChildren()) {
                children.add(buildEasyUITreeObj(childTreeModel));
            }

            departmentTreeObj.put("children", children);
        }

        return departmentTreeObj;
    }
}