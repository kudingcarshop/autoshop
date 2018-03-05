package com.kuding.commons.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {
	
	public static final int FLAG_SUCCESS = 1;
	public static final int FLAG_FAIL = 0;
	
	//1：success 0: fail
	private int flag = FLAG_FAIL;
	
	private String message;
	
	private Map<String,Object> extraData= new HashMap<String,Object>();
	
	private List<?> rows;


	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getExtraData() {
		return extraData;
	}

	public void setExtraData(Map<String, Object> extraData) {
		this.extraData = extraData;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
