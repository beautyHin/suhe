package com.cms.liveout.action;

import com.cms.liveout.bean.LiveOutBean;
import com.cms.liveout.dao.ILiveOutDao;
import com.cms.liveout.dao.impl.LiveOutDao;
import com.cms.liveout.model.LiveOutQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;

public class LiveOutStudentAction extends BaseAction implements ModelDriven<LiveOutQuery>{

	private LiveOutQuery query;
	private Integer pageCode;
	private ILiveOutDao liveoutDao;
	/*
	 * 查看本人旷寝记录
	 */
	public String list() {
		liveoutDao = new LiveOutDao();

		PageBean<LiveOutBean> pageBean = query.getPageBean();
		pageBean.setpageCode(pageCode == null ? 1 :pageCode);
		pageBean.setpageSize(10);
		query.setStuCode(getUser().getCode()); 	//获取用户学号
		query = liveoutDao.findPage(query);
		saveQuery(query);
		return "/jsp/cms/student/liveoutList.jsp";
	}
	
	@Override
	public LiveOutQuery getModel() {
		if(query == null) 
			query = new LiveOutQuery();
		return query;
	}

	public LiveOutQuery getQuery() {
		return query;
	}

	public void setQuery(LiveOutQuery query) {
		this.query = query;
	}

	public Integer getPageCode() {
		return pageCode;
	}

	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}

	public ILiveOutDao getLiveoutDao() {
		return liveoutDao;
	}

	public void setLiveoutDao(ILiveOutDao liveoutDao) {
		this.liveoutDao = liveoutDao;
	}
}
