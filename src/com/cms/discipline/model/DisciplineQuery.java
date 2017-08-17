package com.cms.discipline.model;

import com.cms.discipline.bean.DisciplineBean;
import com.framework.common.PageBean;

public class DisciplineQuery extends DisciplineBean{

	private PageBean<DisciplineBean> pageBean = new PageBean<DisciplineBean>();

	public PageBean<DisciplineBean> getPageBean() {
		return pageBean;
	} 
	
}
