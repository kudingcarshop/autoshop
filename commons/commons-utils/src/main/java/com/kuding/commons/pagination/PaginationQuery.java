package com.kuding.commons.pagination;

import java.util.LinkedList;
import java.util.List;

public class PaginationQuery {
	
	//查询条件
	public final List<Object> params = new LinkedList<Object>();
	
	//第几页
	public int page =1;
	
	//单页记录数
	public int rows = 10;
	
	
	public void addParam(Object param){
		params.add(param);
	}
	
	public Object getParam(int index){
		return params.get(index);
	}

}
