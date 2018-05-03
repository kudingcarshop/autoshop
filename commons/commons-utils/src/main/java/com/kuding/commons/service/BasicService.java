package com.kuding.commons.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.kuding.commons.pagination.PaginationQuery;
import com.kuding.commons.pagination.PaginationResult;



@Service
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(readOnly=true)
	public PaginationResult<?> queryByPagination(PaginationQuery pageQuery,String hql){
		PaginationResult<?> result = new PaginationResult();
		
		int index = hql.indexOf("from");
		if(index > -1){
			String countHql = "select count(*) " + hql.substring(index);
			Query countQuery = getSession().createQuery(countHql);
			if(pageQuery.params != null && pageQuery.params.size() > 0) {
				pushParams(countQuery,pageQuery.params);
			}
			
			if(pageQuery.mapParams != null && pageQuery.mapParams.size() > 0) {
				pushParams(countQuery,pageQuery.mapParams);
			}
			Long rowCount = (Long) countQuery.uniqueResult();
			if(rowCount != null) {
				result.setTotal(rowCount.intValue());
			}
		}
		
		Query query = getSession().createQuery(hql);
		if(pageQuery.params != null && pageQuery.params.size() > 0) {
			pushParams(query,pageQuery.params);
		}
		
		if(pageQuery.mapParams != null && pageQuery.mapParams.size() > 0) {
			pushParams(query,pageQuery.mapParams);
		}
		query.setFirstResult((pageQuery.page-1) * pageQuery.rows);
		query.setMaxResults(pageQuery.rows);
		result.setRows(query.list());
		
		
		return result;
	}
	
	public void pushParams(Query query, List<Object> params){
		if(query != null
				&& params!= null && params.size() > 0){
			for(int i =0 ; i < params.size(); i++){
				if(params.get(i) != null){
					query.setParameter(i, params.get(i));
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public PaginationResult<Map<String,Object>> queryBySqlPagination(PaginationQuery pageQuery,String sql){
		PaginationResult<Map<String,Object>> result = new PaginationResult<>();
		
		String countSql = "select count(*) from ( " + sql +" )";
		SQLQuery countQuery = getSession().createSQLQuery(countSql);
		if(pageQuery != null) {
			if(pageQuery.mapParams!= null && pageQuery.mapParams.size() > 0) {
				pushParams(countQuery, pageQuery.mapParams);
			}
			
			if(pageQuery.params!= null && pageQuery.params.size() > 0) {
				pushParams(countQuery, pageQuery.params);
			}
		}
		result.setTotal(((Long)countQuery.uniqueResult()).intValue());
		
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		if(pageQuery != null) {
			if(pageQuery.mapParams!= null && pageQuery.mapParams.size() > 0) {
				pushParams(query, pageQuery.mapParams);
			}
			
			if(pageQuery.params!= null && pageQuery.params.size() > 0) {
				pushParams(query, pageQuery.params);
			}
		}
		query.setFirstResult((pageQuery.page-1) * pageQuery.rows);
		query.setMaxResults(pageQuery.rows);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		result.setRows(query.list());
		
		return result;
	}
	
	public void pushParams(Query query,Map<String,Object> params){
		if(query != null
				&& params!= null && params.size() > 0){
			for(String key : params.keySet()){
				query.setParameter(key, params.get(key));
			}
		}
	}

}
