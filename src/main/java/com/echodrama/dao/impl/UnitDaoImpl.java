package com.echodrama.dao.impl;

import com.echodrama.dao.UnitDao;
import com.echodrama.pojo.UnitPojo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("unitDao")
public class UnitDaoImpl extends BaseDaoImpl<UnitPojo> implements UnitDao {

    @Override
    public UnitPojo getUnitByCode(String code) throws Exception {
        final String HQL = "from UnitPojo unit where unit.code=:code";

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);

        List<UnitPojo> unitPojoList = this.queryByHql(HQL, map);
        if (unitPojoList.size() == 1) {
            return unitPojoList.get(0);
        } else {
            return null;
        }
    }

}
