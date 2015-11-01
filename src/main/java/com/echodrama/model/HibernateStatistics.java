package com.echodrama.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/7/14
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateStatistics {
    private Map<String, CacheStatistics> cacheStatisticsMap = new HashMap<String, CacheStatistics>();

    public void addCacheStatistics(String regionName, CacheStatistics cacheStatistics) {
        this.cacheStatisticsMap.put(regionName, cacheStatistics);
    }

    public Map<String, CacheStatistics> getCacheStatisticsMap() {
        return cacheStatisticsMap;
    }

    public void setCacheStatisticsMap(Map<String, CacheStatistics> cacheStatisticsMap) {
        this.cacheStatisticsMap = cacheStatisticsMap;
    }

    public class CacheStatistics {
        long hitCount;
        long missCount;
        long putCount;
        long elementCountInMemory;
        long sizeInMemory;

        public long getHitCount() {
            return hitCount;
        }

        public void setHitCount(long hitCount) {
            this.hitCount = hitCount;
        }

        public long getMissCount() {
            return missCount;
        }

        public void setMissCount(long missCount) {
            this.missCount = missCount;
        }

        public long getPutCount() {
            return putCount;
        }

        public void setPutCount(long putCount) {
            this.putCount = putCount;
        }

        public long getElementCountInMemory() {
            return elementCountInMemory;
        }

        public void setElementCountInMemory(long elementCountInMemory) {
            this.elementCountInMemory = elementCountInMemory;
        }

        public long getSizeInMemory() {
            return sizeInMemory;
        }

        public void setSizeInMemory(long sizeInMemory) {
            this.sizeInMemory = sizeInMemory;
        }
    }
}
