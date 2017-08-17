package com.cms.building.model;

import com.cms.building.bean.BuildingBean;
import com.framework.common.PageBean;

public class BuildingQuery extends BuildingBean {
	
	private PageBean<BuildingBean> pageBean = new PageBean<BuildingBean>();

	public PageBean<BuildingBean> getPageBean() {
		return pageBean;
	}
	
}
