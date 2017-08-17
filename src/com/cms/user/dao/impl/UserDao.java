package com.cms.user.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cms.user.bean.UserBean;
import com.cms.user.dao.IUserDao;
import com.framework.common.BaseDao;

public class UserDao extends BaseDao implements IUserDao{
	private ResultSet rs = null; 
	
	/**
	 * 登录
	 */
	@Override
	public UserBean login(UserBean user) {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "select * from tuser u where u.username=?  AND u.password=?";
		Connection con = this.getConnection();
		UserBean userBean = null;
		try {
			
			userBean = queryRunner.query(con, sql, new BeanHandler<>(UserBean.class),user.getUsername(),user.getPassword());
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.closeCon(con);
			}

		return userBean;
	}
	
	@Override
	public int insert(UserBean userBean) {
		Connection con = this.getConnection();
		QueryRunner queryRunner = new QueryRunner();
		int count = 0;
		String sql = "insert into tuser  values (?,?,?,?,?,?,?,?)";

		try {
			count = queryRunner.update(con, sql, beanTOObject(userBean));
		} catch (SQLException e) {
			e.printStackTrace();		
		} finally {
			this.closeCon(con);
		}
		return count;
	}
	
	@Override
	public boolean isExit(UserBean userBean) {
		Connection con = getConnection();
		String param = "";
		String sql = "";
		if(userBean.getUsername()!=null) { 
			 sql = "select count(*) from tuser where username = ?";
			param = userBean.getUsername();
		}
		
		if(userBean.getCode()!=null) { 
			sql = "select count(*) from tuser where code = ?";
			param = userBean.getCode();
		}
		
		QueryRunner queryRunner = new QueryRunner();
		Number count = 0;
		try {
			count = (Number)queryRunner.query(con,sql,new ScalarHandler(),param);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return count.intValue() == 0 ? false : true;
	}

	@Override
	public UserBean getById(Integer id) {
		String sql = "select * from tuser where id = ?";
		QueryRunner queryRunner = new QueryRunner();
		Connection con = getConnection();
		UserBean userBean = new UserBean();
		try {
			userBean = queryRunner.query(con, sql, new BeanHandler<UserBean>(UserBean.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return userBean;
	}
	
	

	@Override
	public int updatePwd(UserBean userBean) {
		int count = 0;
		Connection con = getConnection();
		String sql = "update tuser set password=? where id = ?";
	
		List<Object> list = new ArrayList<Object>();
		list.add(userBean.getPassword());
		list.add(userBean.getId());
		
		System.out.println("SQL:"+sql);
		System.out.println("param:"+list);
		
		QueryRunner queryRunner = new QueryRunner();
		try {
			count = queryRunner.update(con, sql, list.toArray());
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return count;
	}

	public Object[] beanTOObject(UserBean userBean) {
		Object[] objs = {userBean.getId(),userBean.getUsername(),userBean.getPassword(),userBean.getName(),userBean.getCode(),userBean.getRole(),userBean.getPhone(),userBean.getCreateDate()};
		return objs;
		
	}

}
