package com.cms.stu.model;

import com.cms.stu.bean.StudentBean;
import com.framework.common.PageBean;

public class StudentQuery extends StudentBean {
	
	private PageBean<StudentBean> pegeBean = new PageBean<StudentBean>();

	public PageBean<StudentBean> getPegeBean() {
		return pegeBean;
	}


	
}
