package com.kuding.system.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_role")
public class RoleEntity {
	
	@Id
	@Column(name="role_code")
	private String roleCode;
	
	@Column(name="name")
	private String name;
	
	@Column(name="role_desc")
	private String description;
	
	@ManyToMany(mappedBy="roles")
	private Set<UserEntity> users;
	
	@ManyToMany
	@JoinTable(name="sys_role_fun",
			   joinColumns= {@JoinColumn(name="role_code")},
			   inverseJoinColumns= {@JoinColumn(name="fun_id")})
	private Set<FunctionEntity> functions;


	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	public Set<FunctionEntity> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<FunctionEntity> functions) {
		this.functions = functions;
	}

}
