package com.echodrama.web.action;

import java.util.HashMap;
import java.util.Map;

public class BaseAction {

    protected final String KEY_RESULT = "result";

    protected final String KEY_MESSAGE = "message";

    protected final String KEY_DATA = "data";

    protected final String RESULT_SUCCESS = "success";

    protected final String RESULT_FAILURE = "failure";

    public Map<String, Object> buildSuccessMap(Map<String, Object> dataMap) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(KEY_RESULT, RESULT_SUCCESS);
        resultMap.put(KEY_DATA, dataMap);
        return resultMap;
    }

    public Map<String, Object> buildFailureMap(Map<String, Object> dataMap, Exception ex) {
        //ex.printStackTrace();
        System.out.println(ex.getLocalizedMessage());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(KEY_RESULT, RESULT_FAILURE);
        resultMap.put(KEY_MESSAGE, ex.getLocalizedMessage());
        resultMap.put(KEY_DATA, dataMap);

        return resultMap;
    }
}