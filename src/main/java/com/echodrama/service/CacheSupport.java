package com.echodrama.service;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/10/14
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CacheSupport {
    public void clearCache(String[] keys) throws Exception;
}
