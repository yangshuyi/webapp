package com.echodrama.dao.impl;

import com.echodrama.dao.BaseDao;
import com.echodrama.model.PaginationQueryModel;
import com.echodrama.utility.Constants;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl<T> implements BaseDao<T> {
    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
    }

    @Override
    public T save(T entity) {
        getSession().save(entity);
        return entity;
    }

//	 @Override
//	 public T load(Serializable id, boolean lock) {
//	 T entity = null;
//	 if (lock) {
//	 entity = getSession().load(T.class, id, LockMode.UPGRADE);
//	 } else {
//	 entity = (T) this.getSessionFactory().getCurrentSession().load(getPersistentClass(), id);
//	 }
//	 return entity;
//	 }
    //

    @Override
    public T get(Class clazz, Serializable id) {

        return (T) getSession().get(clazz, id);
    }
    //
    // @Override
    // @Transactional(readOnly = true)
    // public T load(Serializable id) {
    // return load(id, false);
    // }
    //
    // @Override
    // @Transactional(readOnly = true)
    // public List<T> findAll() {
    // return findByCriteria();
    // }
    //
    // @SuppressWarnings("unchecked")
    // @Override
    // @Transactional(readOnly = true)
    // public List<T> findAll(OrderBy... orders) {
    // Criteria crit = createCriteria();
    // if (orders != null) {
    // for (OrderBy order : orders) {
    // crit.addOrder(order.getOrder());
    // }
    // }
    // return crit.list();
    // }
    //
    // @Override
    // @Transactional(readOnly = true)
    // public PaginationQueryModel findAll(int pageNo, int pageSize, OrderBy... orders) {
    // Criteria crit = createCriteria();
    // return findByCriteria(crit, pageNo, pageSize, null, OrderBy.asOrders(orders));
    // }
    //
    // @SuppressWarnings("unchecked")
    // @Override
    // @Transactional(readOnly = true)
    // public List<T> findByProperty(String property, Object value) {
    // Assert.hasText(property);
    // return createCriteria(Restrictions.eq(property, value)).list();
    // }
    //
    // @SuppressWarnings("unchecked")
    // @Override
    // @Transactional(readOnly = true)
    // public T findUniqueByProperty(String property, Object value) {
    // Assert.hasText(property);
    // Assert.notNull(value);
    // return (T) createCriteria(Restrictions.eq(property, value)).uniqueResult();
    // }
    //
    // @Override
    // @Transactional(readOnly = true)
    // public int countByProperty(String property, Object value) {
    // Assert.hasText(property);
    // Assert.notNull(value);
    // return ((Number) (createCriteria(Restrictions.eq(property, value)).setProjection(Projections.rowCount()).uniqueResult())).intValue();
    // }
    //
    //


    @Override
    public List<T> listAll(Class<T> clazz) throws Exception {
        Criteria query = getSession().createCriteria(clazz);
//        query.setCacheable(true);
        return query.list();
    }

    @Override
    public List<T> queryByHql(String hql, Map<String, Object> paramMap) {
        Query query = getSession().createQuery(hql);

        if (paramMap != null) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                if (entry.getValue().getClass().isArray()) {
                    query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
                } else {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
        }

//        query.setCacheable(true);
        return query.list();
    }


    @Override
    public List<T> queryByHql(String hql, Collection<PaginationQueryModel<T>.CriterialItem> criterialItems) throws Exception {
        return queryByHql(hql, criterialItems, Constants.INVALID_NUMBER, Constants.INVALID_NUMBER);
    }

    @Override
    public List<T> queryByHql(String hql, Collection<PaginationQueryModel<T>.CriterialItem> criterialItems, int firstRecord, int maxResults) throws Exception {
        Query query = getSession().createQuery(hql);
        if (firstRecord != Constants.INVALID_NUMBER) {
            query.setFirstResult(firstRecord);
        }

        if (maxResults != Constants.INVALID_NUMBER) {
            query.setMaxResults(maxResults);
        }

        if (criterialItems != null) {
            for (PaginationQueryModel.CriterialItem criterialItem : criterialItems) {
                switch (criterialItem.getOperationType()) {
                    case eq:
                    case gt:
                    case lt:
                        query.setParameter(criterialItem.getAlias(), criterialItem.getValue());
                        break;
                    case in:
                        query.setParameterList(criterialItem.getAlias(), (Object[]) criterialItem.getValue());
                        break;
                    case like:
                        query.setParameter(criterialItem.getAlias(), "%" + criterialItem.getValue() + "%");
                        break;
                    default:
                        throw new Exception("no expected operation type [" + criterialItem.getOperationType() + "]");
                }
            }
        }

//        query.setCacheable(true);
        return query.list();
    }

    @Override
    public int countByHql(String hql, Collection<PaginationQueryModel<T>.CriterialItem> criterialItems) throws Exception {
        Query query = getSession().createQuery(hql);

        if (criterialItems != null) {
            for (PaginationQueryModel.CriterialItem criterialItem : criterialItems) {
                switch (criterialItem.getOperationType()) {
                    case eq:
                    case gt:
                    case lt:
                        query.setParameter(criterialItem.getAlias(), criterialItem.getValue());
                        break;
                    case in:
                        query.setParameterList(criterialItem.getAlias(), (Object[]) criterialItem.getValue());
                        break;
                    case like:
                        query.setParameter(criterialItem.getAlias(), "%" + criterialItem.getValue() + "%");
                        break;
                    default:
                        throw new Exception("no expected operation type [" + criterialItem.getOperationType() + "]");
                }
            }
        }

//        query.setCacheable(true);
        return ((Number) query.uniqueResult()).intValue();
    }

    @Override
    public T update(T entity) {
        getSession().update(entity);
        return entity;
    }

    @Override
    public T saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    // @Override
    // public Object merge(Object entity) {
    // Assert.notNull(entity);
    // return getSession().merge(entity);
    // }
    //
    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

//	@Override
//	public void deleteById(Serializable id) {
//		T entity = load(id);
//		getSession().delete(entity);
//	}

    //
    // @Override
    // public void refresh(Object entity) {
    // getSession().refresh(entity);
    //
    // }
    //
    //
    // /**
    // * 按Criterion查询对象列表.
    // *
    // * @param criterion
    // * 数量可变的Criterion.
    // */
    // @SuppressWarnings("unchecked")
    // protected List<T> findByCriteria(Criterion... criterion) {
    // return createCriteria(criterion).list();
    // }
    //
    // /**
    // * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
    // */
    // protected Criteria createCriteria(Criterion... criterions) {
    // Criteria criteria = getSession().createCriteria(getPersistentClass());
    // for (Criterion c : criterions) {
    // criteria.add(c);
    // }
    // return criteria;
    // }
    //
    // @SuppressWarnings("rawtypes")
    // protected PaginationQueryModel findByCriteria(Criteria crit, int pageNo, int pageSize, Projection projection, Order... orders) {
    // int totalCount = ((Number) crit.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    // PaginationQueryModel p = new PaginationQueryModel(pageNo, pageSize, totalCount);
    // if (totalCount < 1) {
    // p.setList(new ArrayList());
    // return p;
    // }
    // crit.setProjection(projection);
    // if (projection == null) {
    // crit.setResultTransformer(Criteria.ROOT_ENTITY);
    // }
    // if (orders != null) {
    // for (Order order : orders) {
    // crit.addOrder(order);
    // }
    // }
    // crit.setFirstResult(p.getFirstResult());
    // crit.setMaxResults(p.getPageSize());
    // p.setList(crit.list());
    // return p;
    // }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

}
