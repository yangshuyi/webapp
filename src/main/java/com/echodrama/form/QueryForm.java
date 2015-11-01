package com.echodrama.form;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.echodrama.enumtype.SortOrderEnum;
import com.echodrama.model.PaginationQueryModel;
import com.echodrama.utility.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: shuyi
 * Date: 13-12-28
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
public abstract class QueryForm {
    protected String paramJSONStr = null;

    protected int rows = Constants.INVALID_NUMBER;
    protected int page = Constants.INVALID_NUMBER;
    protected String sort = null;
    protected String order = null;

    public String getParamJSONStr() {
        return paramJSONStr;
    }

    public void setParamJSONStr(String paramJSONStr) {
        this.paramJSONStr = paramJSONStr;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public PaginationQueryModel toQueryModel() throws Exception {
        PaginationQueryModel model = new PaginationQueryModel();

        if (!StringUtils.isEmpty(paramJSONStr)) {
            JSONArray paramArray = JSON.parseArray(paramJSONStr);
            int paramSize = paramArray.size();
            for (int i = 0; i < paramSize; i++) {
                JSONObject param = paramArray.getJSONObject(i);
                String name = param.getString("name");
                if (StringUtils.isEmpty(name)) {
                    continue;
                }

                String alias = param.getString("alias");
                String type = param.getString("type");
                String operationTypeKey = param.getString("operationType");
                Object val = null;
                if (operationTypeKey.equals("in")) {
                    JSONArray valueArray = param.getJSONArray("value");
                    Object[] objArray = new Object[valueArray.size()];
                    if ("String".equalsIgnoreCase(type)) {
                        int valueSize = valueArray.size();
                        for (int j = 0; j < valueSize; j++) {
                            objArray[j] = valueArray.getString(j);
                        }
                    } else if ("Integer".equalsIgnoreCase(type)) {
                        int valueSize = valueArray.size();
                        for (int j = 0; j < valueSize; j++) {
                            objArray[j] = valueArray.getInteger(j);
                        }
                    } else {
                        throw new Exception("no suitable type");
                    }
                    val = objArray;

                } else {
                    Object obj = null;
                    if ("String".equalsIgnoreCase(type)) {
                        obj = param.getString("value");
                    } else if ("Integer".equalsIgnoreCase(type)) {
                        obj = param.getInteger("value");
                    } else if ("Boolean".equalsIgnoreCase(type)) {
                        obj = param.getBoolean("value");
                    } else {
                        throw new Exception("no suitable type");
                    }

                    val = obj;
                }

                model.addParam(name, alias, operationTypeKey, val);
            }
        }

        if (Constants.INVALID_NUMBER != page && Constants.INVALID_NUMBER != rows) {
            int firstRecord = (page - 1) * rows;
            int maxResults = rows;

            model.setFirstRecord(firstRecord);
            model.setMaxResults(maxResults);
        }

        if (this.getSort() != null) {
            model.setSort(this.getSort());
            model.setOrder(SortOrderEnum.valueOf(this.getOrder()));
        }

        return model;
    }
}
