package com.echodrama.service;

import com.echodrama.model.Unit;

public interface UnitService {

    public Unit loadUnit(String id) throws Exception;

    public Unit loadUnitByCode(String code) throws Exception;

    public Unit initNewUnit() throws Exception;

    public void saveUnit(Unit unit) throws Exception;
}
