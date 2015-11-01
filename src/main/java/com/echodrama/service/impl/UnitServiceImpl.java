package com.echodrama.service.impl;

import com.echodrama.dao.UnitDao;
import com.echodrama.model.Unit;
import com.echodrama.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("unitService")
public class UnitServiceImpl implements UnitService {
    //    @Autowired
    //    private MemcachedClient memcachedClient;

    @Autowired
    private UnitDao unitDao;


    @Override
    public Unit loadUnit(String id) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Unit loadUnitByCode(String code) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Unit initNewUnit() throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void saveUnit(Unit unit) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
