package com.echodrama.dao;

import com.echodrama.pojo.UnitPojo;

public interface UnitDao extends BaseDao<UnitPojo> {

    public UnitPojo getUnitByCode(String code) throws Exception;
}
