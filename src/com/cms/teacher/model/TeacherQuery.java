package com.cms.teacher.model;

import com.cms.teacher.bean.TeacherBean;
import com.framework.common.PageBean;

public class TeacherQuery extends TeacherBean{

	private PageBean<TeacherBean> pageBean = new PageBean<TeacherBean>();

	public PageBean<TeacherBean> getPageBean() {
		return pageBean;
	}
	
}
