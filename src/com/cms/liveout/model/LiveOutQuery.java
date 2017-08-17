package com.cms.liveout.model;

import com.cms.liveout.bean.LiveOutBean;
import com.framework.common.PageBean;

public class LiveOutQuery extends LiveOutBean{

	private PageBean<LiveOutBean> pageBean = new PageBean<LiveOutBean>();

	public PageBean<LiveOutBean> getPageBean() {
		return pageBean;
	}
	
}
