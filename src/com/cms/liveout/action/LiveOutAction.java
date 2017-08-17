package com.cms.liveout.action;

import com.cms.liveout.bean.LiveOutBean;
import com.cms.liveout.dao.ILiveOutDao;
import com.cms.liveout.dao.impl.LiveOutDao;
import com.cms.liveout.model.LiveOutQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LiveOutAction extends BaseAction implements ModelDriven<LiveOutBean>,Preparable {

	private static final long serialVersionUID = -7833369100883365549L;

	private LiveOutBean liveOutBean;
	private LiveOutQuery query;
	private ILiveOutDao liveOutDao;

	private String[] items;
	private Integer pageCode;
	private String listAction = "!/cms/teacher/liveout/LiveOut/list.action";
	public String getJspPath(String _jsp) {
		
		return "/jsp/cms/teacher/liveout/liveout"+_jsp;
		
	}
	public String insert() {
		liveOutDao = new LiveOutDao();
		
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		int count = liveOutDao.insert(query);

		if(count > 0) {
			this.updateSuccess("添加成功");
		} else {
			this.updateError("失败,服务器异常!");
		}
		
		return listAction;
	}
	
	public String update() {
		liveOutDao = new LiveOutDao();
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		if(liveOutDao.update(query) > 0) {
			this.updateSuccess("修改成功");
		} else {
			this.updateError("失败,服务器异常!");
		}
		
		return listAction;
	}
	
	public String edit() {
		liveOutDao = new LiveOutDao();
		liveOutBean = liveOutDao.getById(getRequest().getParameter("liveoutId"));
		return getJspPath("Edit.jsp");
	}
	
	public String deleteBatch() {
		liveOutDao = new LiveOutDao();
		List<String> LiveOutIds = new ArrayList<String>();
		for(String item : items) {
			LiveOutIds.add(item);
		}
		
		if(liveOutDao.deleteBatch(LiveOutIds) > 0){
			this.updateSuccess("删除成功");
		} else {
			this.updateError("失败,服务器异常!");
		}
		return listAction;
	}
	
	public String list() {
		PageBean<LiveOutBean> pageBean = query.getPageBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode==null ? 1 : pageCode);
		liveOutDao = new LiveOutDao();
		query = liveOutDao.findPage(query);
		saveQuery(query);
		return getJspPath("List.jsp");
		
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
	
	public String[] getItems() {
		return items;
	}
	public void setItems(String[] items) {
		this.items = items;
	}
	@Override
	public void prepare() throws Exception {
        liveOutBean = new LiveOutBean();
	}

	@Override
	public LiveOutBean getModel() {
		return liveOutBean;
	}

	
	public String toLiveOutList() {
		return getJspPath("List.jsp");
	}
	
	public String toLiveOutInsert() {
		return getJspPath("Insert.jsp");
		
	}
	
	
}
