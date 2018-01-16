package com.kuding.commons.login;

import java.util.HashSet;
import java.util.Set;

public class UserInfo {
	
	private String userName;
	
	private Integer state;
	
	private Set<String> roles = new HashSet<String>();
	
	private Set<String> functions = new HashSet<String>();

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

	public Set<String> getRoles() {
		return roles;
	}

	public Set<String> getFunctions() {
		return functions;
	}


}
