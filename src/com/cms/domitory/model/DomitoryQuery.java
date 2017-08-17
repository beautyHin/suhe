package com.cms.domitory.model;

import com.cms.domitory.bean.DomitoryBean;
import com.framework.common.PageBean;

public class DomitoryQuery extends DomitoryBean {

	private PageBean<DomitoryBean> pageBean = new PageBean<DomitoryBean>();

	public PageBean<DomitoryBean> getPageBean() {
		return pageBean;
	}

	
}
