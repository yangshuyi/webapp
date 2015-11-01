package com.echodrama.dao;

import com.echodrama.model.PaginationQueryModel;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {

    // 1.get()采用立即加载方式,而load()采用延迟加载; get()方法执行的时候,会立即向数据库发出查询语句,而load()方法返回的是一个代理(此代理中只有一个id属性),只有等真正使用该对象属性的时候,才会发出sql语句
    // 2.如果数据库中没有对应的记录,get()方法返回的是null.而load()方法出现异常ObjectNotFoundException
//	public T load(Serializable id);
//
//	// 锁模式可以禁止不可重复读取发生
//	public T load(Serializable id, boolean lock);
//
    public T get(Class clazz, Serializable id);

//	public List<T> findAll();

//	public List<T> findAll(OrderBy... orders);
//
//	public PaginationQueryModel findAll(int pageNo, int pageSize, OrderBy... orders);

    //	/**
//	 * 按属性查找对象列表.
//	 */
//	public List<T> findByProperty(String property, Object value);
//
//	/**
//	 * 按属性查找唯一对象.
//	 */
//	public T findUniqueByProperty(String property, Object value);
//
//	/**
//	 * 按属性查找对象的数量
//	 * 
//	 * @param property
//	 * @param value
//	 * @return
//	 */
//	public int countByProperty(String property, Object value);
//
    public List<T> listAll(Class<T> clazz) throws Exception;

    public List<T> queryByHql(String hql, Map<String, Object> paramMap);

    public List<T> queryByHql(String hql, Collection<PaginationQueryModel<T>.CriterialItem> criterialItems) throws Exception;

    public List<T> queryByHql(String hql, Collection<PaginationQueryModel<T>.CriterialItem> criterialItems, int firstRecord, int maxResult) throws Exception;

    public int countByHql(String hql, Collection<PaginationQueryModel<T>.CriterialItem> criterialItems) throws Exception;

    public T save(T entity);

    public T update(T entity);

    public T saveOrUpdate(T entity);
//
//	/**
//	 * 保存或更新对象拷贝
//	 * 
//	 * @param entity
//	 * @return 已更新的持久化对象
//	 */
//	public Serializable merge(Object entity);
//

    public void delete(T entity);

//	public void deleteById(Serializable id);
//
//	/**
//	 * 刷新对象
//	 * 
//	 * @param entity
//	 */
//	public void refresh(Object entity);
//
//	/**
//	 * 获得实体Class
//	 * 
//	 * @return
//	 */
//	public Class<T> getPersistentClass();
//
//	/**
//	 * 创建实体类的对象
//	 * 
//	 * @return
//	 */
//	public T createNewEntiey();
}
