package com.cms.building.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cms.building.bean.BuildingBean;
import com.cms.building.dao.IBuildingDao;
import com.cms.building.dao.impl.BuildingDao;
import com.cms.building.model.BuildingQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BuildingAction extends BaseAction implements ModelDriven,Preparable {

	private BuildingQuery query;
	private IBuildingDao buildingDao;

	private String[] items;
	private Integer pageCode;
	private String listAction = "!/cms/teacher/building/Building/list.action";
	public String getJspPath(String _jsp) {
		
		return "/jsp/cms/teacher/building/building"+_jsp;
		
	}
	public String insert() {
		buildingDao = new BuildingDao();
		
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		int count = buildingDao.insert(query);
		
		if(count > 0) {
			this.updateSuccess("添加成功");

		} else {
			this.updateError("失败,服务器异常!");
		}
		
		return listAction;
	}
	
	public String update() {
		buildingDao = new BuildingDao();
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		if(buildingDao.update(query) > 0) {
			this.updateSuccess("修改成功");

		} else {
			this.updateError("失败,服务器异常!");
		}
		
		return listAction;
	}
	
	public String edit() {
		buildingDao = new BuildingDao();
		BuildingBean buildingBean = buildingDao.getById(getRequest().getParameter("buildingId"));
		getRequest().setAttribute("model", buildingBean);
		return getJspPath("Edit.jsp");
	}
	
	public String deleteBatch() {
		buildingDao = new BuildingDao();
		List<String> buildingIds = new ArrayList<String>();
		for(String item : items) {
			buildingIds.add(item);
		}
		
		if(buildingDao.deleteBatch(buildingIds) > 0){
			this.updateSuccess("删除成功");

		} else {
			this.updateError("失败,服务器异常!");
		}
		return listAction;
	}
	
	public String list() {
		PageBean<BuildingBean> pageBean = query.getPageBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode==null ? 1 : pageCode);
		buildingDao = new BuildingDao();
		query = buildingDao.findPage(query);
			
		saveQuery(query);
		
		return getJspPath("List.jsp");
		
	}
	
	public BuildingQuery getQuery() {
		return query;
	}

	public void setQuery(BuildingQuery query) {
		this.query = query;
	}

	public Integer getPageCode() {
		return pageCode;
	}
	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
	
	public String[] getItems() {
		return items;
	}
	public void setItems(String[] items) {
		this.items = items;
	}
	@Override
	public void prepare() throws Exception {
		query = new BuildingQuery();
	}

	@Override
	public Object getModel() {
		return query;
	}

	
	public String toBuildingList() {
		return getJspPath("List.jsp");
	}
	
	public String create() {
		return getJspPath("Insert.jsp");
		
	}
	
	
}
