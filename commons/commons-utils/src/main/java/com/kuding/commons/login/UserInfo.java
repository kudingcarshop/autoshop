package com.kuding.commons.login;

import java.util.HashSet;
import java.util.Set;

public class UserInfo {

	private Integer userId;
	
	private String userName;
	
	private Integer state;
	
	private Integer garageId;
	
	private Integer customerId;
	
	private Set<String> roles = new HashSet<String>();
	
	private Set<String> functions = new HashSet<String>();
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getGarageId() {
		return garageId;
	}

	public void setGarageId(Integer garageId) {
		this.garageId = garageId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public Set<String> getFunctions() {
		return functions;
	}


}
