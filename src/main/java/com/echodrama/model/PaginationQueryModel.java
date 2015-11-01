package com.echodrama.model;

import com.echodrama.enumtype.SortOrderEnum;
import com.echodrama.utility.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: shuyi
 * Date: 13-12-28
 * Time: 下午1:18
 * To change this template use File | Settings | File Templates.
 */
public class PaginationQueryModel<T> {
    private Map<String, CriterialItem> paramMap = new HashMap<String, CriterialItem>();

    private int firstRecord = Constants.INVALID_NUMBER;
    private int maxResults = Constants.INVALID_NUMBER;

    private int total = Constants.INVALID_NUMBER;

    private SortOrderEnum order = null;
    private String sort = null;

    private List<T> rows = null;

    public PaginationQueryModel() {

    }

    public void addParam(String key, String alias, String operationTypeKey, Object value) {
        this.paramMap.put(key, new CriterialItem(key, alias, OperationTypeEnum.valueOf(operationTypeKey), value));
    }

    public Map<String, CriterialItem> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, CriterialItem> paramMap) {
        this.paramMap = paramMap;
    }

    public int getFirstRecord() {
        return firstRecord;
    }

    public void setFirstRecord(int firstRecord) {
        this.firstRecord = firstRecord;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public SortOrderEnum getOrder() {
        return order;
    }

    public void setOrder(SortOrderEnum order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public String buildQueryHQL() throws Exception {
        StringBuilder builder = new StringBuilder();
        for (PaginationQueryModel.CriterialItem criterialItem : this.getParamMap().values()) {
            switch (criterialItem.getOperationType()) {
                case eq:
                    builder.append(" AND ").append(criterialItem.getName()).append("=:").append(criterialItem.getAlias());
                    break;
                case like:
                    builder.append(" AND ").append(criterialItem.getName()).append(" like :").append(criterialItem.getAlias());
                    break;
                case in:
                    builder.append(" AND ").append(criterialItem.getName()).append(" in :").append(criterialItem.getAlias());
                    break;
                case gt:
                    builder.append(" AND ").append(criterialItem.getName()).append(">:").append(criterialItem.getAlias());
                    break;
                case lt:
                    builder.append(" AND ").append(criterialItem.getName()).append("<:").append(criterialItem.getAlias());
                    break;
                default:
                    throw new Exception("no expected operation type [" + criterialItem.getOperationType() + "]");
            }
        }
        return builder.toString();
    }

    public enum OperationTypeEnum {
        eq, like, in, gt, lt;
    }

    public class CriterialItem {
        private String name;
        private String alias;
        private Object value;
        private OperationTypeEnum operationType;

        public CriterialItem(String name, String alias, OperationTypeEnum operationType, Object value) {
            this.name = name;
            this.alias = alias;
            this.operationType = operationType;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public OperationTypeEnum getOperationType() {
            return operationType;
        }

        public void setOperationType(OperationTypeEnum operationType) {
            this.operationType = operationType;
        }
    }
}
