package com.cms.user.dao;

import com.cms.user.bean.UserBean;

public interface IUserDao {
	
	/**
	 * 登录
	 * @return
	 */
	public UserBean login(UserBean userBean);

	/**
	 * 注册
	 * @param userBean
	 * @return
	 */
	public int insert(UserBean userBean);
	
	public boolean isExit(UserBean userBean);
	
	public UserBean getById(Integer id);
	
	public int updatePwd(UserBean userBean);
	
}
