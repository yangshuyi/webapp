package com.echodrama.entityinterceptor;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by shuyi on 14-2-12.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final String DATA_SOURCE_NAME_PREFIX = "dataSource_";

    @Override
    public void setTargetDataSources(Map targetDataSources) {
        super.setTargetDataSources(targetDataSources);
    }

    @Override
    public Object unwrap(Class iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class iface) throws SQLException {
        return false;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        //String unitId = (String) ThreadLocalUtil.getObject(ThreadLocalUtil.KEY_UNIT_ID);
        //return DATA_SOURCE_NAME_PREFIX + unitId;

        return null;
    }
}