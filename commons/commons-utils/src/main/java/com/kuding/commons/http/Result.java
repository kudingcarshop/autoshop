package com.kuding.commons.http;

public class Result {
	
	public static final int FLAG_SUCCESS = 1;
	public static final int FLAG_FAIL = 0;
	
	//1：success 0: fail
	private int flag = FLAG_SUCCESS;
	
	private String message;


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

}
