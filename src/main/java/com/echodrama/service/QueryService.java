package com.echodrama.service;

import com.echodrama.model.QueryResult;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 12/4/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
public interface QueryService {
    QueryResult queryKeyword(String keyword);
}
