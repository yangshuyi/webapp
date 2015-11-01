package com.echodrama.entityinterceptor;

import java.io.Serializable;
import java.util.Date;

import com.echodrama.pojo.BasePojo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * Created with IntelliJ IDEA.
 * User: shuyi
 * Date: 13-12-29
 * Time: 上午9:25
 * To change this template use File | Settings | File Templates.
 */
public class PersistEntityInterceptor extends EmptyInterceptor {
    private static final String[] UNIT_TABLE_NAME_ARRAY = new String[]{"T_DEPARTMENT"};

    /*
     * entity - POJO对象
     * id - POJO对象的主键
     * state - POJO对象的每一个属性所组成的集合(除了ID)
     * propertyNames - POJO对象的每一个属性名字组成的集合(除了ID)
     * types - POJO对象的每一个属性类型所对应的Hibernate类型组成的集合(除了ID)
     *
     * 拦截器的操作会直接反映到数据库中的，但是如果返回值是false的话，Hibernate会产生一条Update SQL语句将拦截器的操作结果取消了
     */
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof BasePojo) {
            BasePojo baseModel = ((BasePojo) entity);
            if (baseModel.getCreatedDate() == null) {
                baseModel.setCreatedDate(new Date());
            }

            baseModel.setModifedDate(new Date());
        }

        return true;
    }


    public String onPrepareStatement(String sql) {
        String unitId = (String) ThreadLocalUtil.getObject(ThreadLocalUtil.KEY_UNIT_ID);
        if (StringUtils.isEmpty(unitId)) {
            return sql;
        }

        for (String unitTableName : UNIT_TABLE_NAME_ARRAY) {
            sql = sql.replace(unitTableName, unitTableName + "_" + unitId);//有没有更高效的写法，请指点
        }

        System.out.println("=======================================================================");
        System.out.println(sql);
        System.out.println("=======================================================================");

        return sql;
    }
}
