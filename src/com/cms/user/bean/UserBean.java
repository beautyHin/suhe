package com.cms.user.bean;

import java.util.Date;

/**
 * 用户类(TABLE TUSER)
 *
 */
public class UserBean {

	// 学生
	public static final Integer  USER_STUDENT = 1; 
	//宿舍管理员
	public static final Integer USER_SHUSE_ADMIN = 2;
	//管理员
	public static final Integer USER_ADMIN = 3;
	
	/**
	 * Id
	 */
	private Integer id;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 工号
	 */
	private String code;
	
	/**
	 * 角色
	 */
	private Integer role;

	/**
	 * 联系电话
	 */
	
	private String phone;
	/**
	 *注册日期 
	 */
	private Date createDate;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", code="
				+ code + ", role=" + role + ", phone=" + phone + ", createDate=" + createDate + "]";
	}

	}
