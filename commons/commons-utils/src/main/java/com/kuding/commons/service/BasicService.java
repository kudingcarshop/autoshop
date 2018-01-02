package com.kuding.commons.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.kuding.commons.pagination.PaginationQuery;
import com.kuding.commons.pagination.PaginationResult;



@Service
@Transactional(readOnly = true)
public class BasicService<T>{

	public Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void flush() {
		getSession().flush();
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class, RuntimeException.class })
	public void save(T entity) {
		Assert.notNull(entity, "entity should not be empty");
		getSession().save(entity);
	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class, RuntimeException.class })
	public void delete(Class<T> clasz, Serializable id) {
		Assert.notNull(id, "id should not be null");
		getSession().delete(findById(clasz, id));

	}

	@Transactional(readOnly = false, rollbackFor = { Exception.class, RuntimeException.class })
	public void update(T entity) {
		Assert.notNull(entity, "entity should not be empty");
		getSession().update(entity);
	}

	@SuppressWarnings("unchecked")
	public T findById(Class<T> clasz, Serializable id) {
		return (T) getSession().get(clasz, id);
	}
	
	@SuppressWarnings("unchecked")
	public PaginationResult<T> queryByPagination(PaginationQuery pageQuery,String hql){
		PaginationResult<T> result = new PaginationResult<T>();
		
		int index = hql.indexOf("from");
		if(index > -1){
			String countHql = "select count(*) " + hql.substring(index);
			Query countQuery = getSession().createQuery(countHql);
			pushParams(countQuery,pageQuery.params);
			result.setTotal(((Long)countQuery.uniqueResult()).intValue());
		}
		
		Query query = getSession().createQuery(hql);
		pushParams(query,pageQuery.params);
		query.setFirstResult((pageQuery.page-1) * pageQuery.rows);
		query.setMaxResults(pageQuery.rows);
		result.setRows(query.list());
		
		
		return result;
	}
	
	private void pushParams(Query query, List<Object> params){
		if(query != null
				&& params!= null && params.size() > 0){
			for(int i =0 ; i < params.size(); i++){
				if(params.get(i) != null){
					query.setParameter(i, params.get(i));
				}
			}
		}
	}

}
