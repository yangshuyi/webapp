package com.echodrama.entityinterceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shuyi on 14-2-12.
 */
public class ThreadLocalUtil {
    public static final String KEY_UNIT_ID = "KEY_UNIT_ID";


    private static final ThreadLocal<Map<String, Object>> contextHolder = new ThreadLocal<Map<String, Object>>();

    public static void setObject(String key, Object obj) {
        Map<String, Object> map = contextHolder.get();
        if (map == null) {
            map = new HashMap<String, Object>();
            contextHolder.set(map);
        }
        map.put(key, obj);
    }


    public static Object getObject(String key) {
        return contextHolder.get() == null ? null : contextHolder.get().get(key);
    }


    public static void remove() {
        contextHolder.remove();
    }
}

