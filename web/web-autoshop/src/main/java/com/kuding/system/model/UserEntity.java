package com.kuding.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name="sys_user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name="login_name")
    private String loginName;

    @Column(name="password")
    private String password;
    
    @Column(name = "state")
    private Integer state;

    @Column(name="create_time")
    private Timestamp createTime;

    @Column(name="last_modify_time")
    private Timestamp lastModifiedTime;

    @Column(name="last_modify_user")
    private String lastModifiedUser;
    
	
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }


}
