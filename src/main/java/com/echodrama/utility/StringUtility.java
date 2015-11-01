package com.echodrama.utility;

import com.echodrama.model.BaseModel;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 1/26/14
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtility {

    public static String codeFormat(int value, int length) {
        String bigNnumber = String.valueOf(1000000000 + value);
        return bigNnumber.substring(bigNnumber.length() - length);
    }

    public static boolean isValueChanged(Object newValue, Object oldValue) {
        if (newValue instanceof BaseModel || oldValue instanceof BaseModel) {
            return isValueChanged((BaseModel) newValue, (BaseModel) oldValue);
        }

        if (newValue == null) {
            if (oldValue == null) {
                return false;
            } else {
                return true;
            }
        }
        return !newValue.equals(oldValue);
    }

    public static boolean isValueChanged(BaseModel newValue, BaseModel oldValue) {
        if (newValue == null) {
            if (oldValue == null) {
                return false;
            } else {
                return true;
            }
        }

        if (oldValue == null) {
            return true;
        }

        if (newValue.getId() == null) {
            if (oldValue.getId() == null) {
                return false;
            } else {
                return true;
            }
        }

        return !newValue.getId().equals(oldValue.getId());
    }

}
