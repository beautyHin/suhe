package com.cms.building.action;

import com.cms.building.bean.BuildingBean;
import com.cms.building.dao.IBuildingDao;
import com.cms.building.dao.impl.BuildingDao;
import com.cms.building.model.BuildingQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;

public class BuildingStudentAction  extends BaseAction implements ModelDriven<BuildingQuery>{

	private IBuildingDao buildingDao;
	private Integer pageCode;
	private BuildingQuery query;
	
	/**
	 * 楼宇信息
	 * 
	 */
	public String list() {
		System.out.println(pageCode);
		buildingDao = new BuildingDao();
		PageBean<BuildingBean> pageBean = query.getPageBean();
		pageBean.setpageCode(pageCode == null?1:pageCode);
		pageBean.setpageSize(10);
		query = buildingDao.findPage(query);
		saveQuery(query);
		return "/jsp/cms/student/buildingList.jsp";
	}
	
	
	@Override
	public BuildingQuery getModel() {
		if(query == null) 
			query = new BuildingQuery();
		return query;
	}

	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
}
