package com.cms.user.action;

import java.io.IOException;
import java.util.Date;

import com.cms.user.bean.UserBean;
import com.cms.user.dao.IUserDao;
import com.cms.user.dao.impl.UserDao;
import com.framework.common.BaseAction;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserAction extends BaseAction implements ModelDriven,Preparable{
	
	private UserBean userBean;
	private IUserDao userDao;
	// 登录
	public void login() throws IOException {
		userDao = new UserDao();
		UserBean user = userDao.login(userBean);
		String isLoginSucess = "";
		if(user != null) {
			user.setPassword("");
			this.getSession().put("user", user);
			isLoginSucess = "true";
		} else {
			isLoginSucess = "false";
		}
		
		getReponse().getWriter().print(isLoginSucess);

}
	
	
	// 注册
	public String signup() {
		userDao = new UserDao();
		UserBean userBean1 = new UserBean();
		userBean1.setUsername(userBean.getUsername());
		if(!userDao.isExit(userBean1)) { //判断用户名是否存在
			userBean1 = new UserBean();
			userBean1.setCode(userBean.getCode());
			if(!userDao.isExit(userBean1)) { //判断学号是否存在
				userBean.setCreateDate(new Date());
				userDao.insert(userBean);
				this.getRequest().setAttribute("msg","注册成功");
			} else { //存在学号
				this.getRequest().setAttribute("msg","学号已注册");
				return "/jsp/signup.jsp";
			}
		} else { //存在用户名
			this.getRequest().setAttribute("msg","用户名已存在");
			return "/jsp/signup.jsp";
		}
		
		return "/jsp/signin.jsp";
		
	}
	
	public String toUpdatePwd() {
		return "/jsp/updatePwd.jsp";
		
	}
	
	public void updatePwd() throws Exception {
		String newPassword = getRequest().getParameter("newPassword");
		String oldPassword = getRequest().getParameter("password");
		
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		userDao = new UserDao();

		//去查库中的旧密码
		UserBean userBean =userDao.getById(getUser().getId());
		userDao.updatePwd(userBean);
		if(userBean.getPassword().equals(oldPassword)) { //旧密码正确更新
			this.getReponse().setContentType("text/json");
			userBean.setPassword(newPassword);
			if(userDao.updatePwd(userBean) > 0 ) {
				jsonObject.put("success", "true");
				jsonObject.put("msg", "密码修改成功");
				jsonArray.add(jsonObject);
				getReponse().getWriter().print(jsonArray);
			} else {
				jsonObject.put("success", "fasle");
				jsonObject.put("msg", "服务器出错,请稍后再试!");
				jsonArray.add(jsonObject);
				getReponse().getWriter().print(jsonArray);
			}
		} else {
			jsonObject.put("success", "false");
			jsonObject.put("msg", "旧密码不正确");
			jsonArray.add(jsonObject);
			getReponse().getWriter().print(jsonArray);
		}
			
	}
	/*
	 * 退出
	 */
	public String userOut() {
		getSession().remove("user"); 
		return "/jsp/userOut.jsp";
	}
	
	@Override
	public Object getModel() {
		return userBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	@Override
	public void prepare() throws Exception {
		userBean = new UserBean();
	}

		
	}
