package com.cms.repair.model;

import com.cms.repair.bean.RepairBean;
import com.framework.common.PageBean;

public class RepairQuery extends RepairBean{

	private PageBean<RepairBean> pageBean = new PageBean<RepairBean>();

	public PageBean<RepairBean> getPageBean() {
		return pageBean;
	}
	
	
}
