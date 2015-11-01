package com.echodrama.service.impl;

import com.echodrama.model.QueryResult;
import com.echodrama.service.QueryService;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 12/4/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryServiceImpl implements QueryService {
    @Override
    public QueryResult queryKeyword(String keyword) {
        QueryResult queryResult = new QueryResult(keyword);

        return queryResult;
    }
}