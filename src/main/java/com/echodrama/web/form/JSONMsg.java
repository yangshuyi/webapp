package com.echodrama.web.form;

import java.util.HashMap;
import java.util.Map;

public class JSONMsg {
    private int status;
    private Map<String, Object> result = new HashMap<String, Object>();
    private String message;
    private String errorCode;

    public void setResultEntry(String key, Object value) {
        this.status = 1;
        this.result.put(key, value);
    }

    public void setErrorInfo(String errorCode, String msg) {
        this.status = -1;
        this.errorCode = errorCode;
        this.message = msg;
    }

    public int getStatus() {
        return status;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
