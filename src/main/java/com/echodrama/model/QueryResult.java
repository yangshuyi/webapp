package com.echodrama.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 12/4/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryResult {
    private String keyword;
    private List<Item> itemList;

    public QueryResult(String keyword) {
        this.keyword = keyword;
        this.itemList = new ArrayList<Item>();
    }

    public void addItem(String threadItemKey, String timesStr, String title) {
        int times = NumberUtils.createInteger(timesStr);
        Item item = new Item(threadItemKey, times, title);
    }

    public String getKeyword() {
        return keyword;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public class Item implements Comparable<Item> {
        private String threadItemKey;
        private int times;
        private String title;

        public Item(String threadItemKey, int times, String title) {
            this.threadItemKey = threadItemKey;
            this.times = times;
            this.title = title;
        }

        public int compareTo(Item item) {
            return this.times - item.times;
        }

        public String getThreadItemKey() {
            return threadItemKey;
        }

        public void setThreadItemKey(String threadItemKey) {
            this.threadItemKey = threadItemKey;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
