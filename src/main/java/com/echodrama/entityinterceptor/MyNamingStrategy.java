package com.echodrama.entityinterceptor;

import org.hibernate.cfg.DefaultNamingStrategy;

import java.util.Calendar;

/**
 * Created by shuyi on 14-2-12.
 */
public class MyNamingStrategy extends DefaultNamingStrategy {
    public static final MyNamingStrategy INSTANCE = new MyNamingStrategy();

    public String classToTableName(String className) {
        return "biz_" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
}

