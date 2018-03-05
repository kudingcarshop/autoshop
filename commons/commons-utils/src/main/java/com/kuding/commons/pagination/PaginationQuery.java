package com.kuding.commons.pagination;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PaginationQuery {
	
	//第几页
	public int page =1;
	
	//单页记录数
	public int rows = 10;

	//查询条件
	public final List<Object> params = new LinkedList<Object>();
	
	public void addParam(Object param){
		params.add(param);
	}
	
	public Object getParam(int index){
		return params.get(index);
	}

	//查询条件
	public final Map<String,Object> mapParams = new HashMap<>();
	
	public void putParam(String key, Object param){
		mapParams.put(key, param);
	}

	public Object getParam(String key){
		return mapParams.get(key);
	}
	
}
