package com.cms.domitory.action;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cms.domitory.bean.DomitoryBean;
import com.cms.domitory.dao.IDomitoryDao;
import com.cms.domitory.dao.impl.DomitoryDao;
import com.cms.domitory.model.DomitoryQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 宿舍管理--宿舍管理员端
 */
public class DomitoryAction extends BaseAction implements ModelDriven<DomitoryQuery> ,Preparable {
	private DomitoryQuery query;
	private IDomitoryDao domitoryDao;

	private String[] items;
	private Integer pageCode;
	private String listAction = "!/cms/teacher/domitory/Domitory/list.action";
	public String getJspPath(String _jsp) {
		
		return "/jsp/cms/teacher/domitory/domitory"+_jsp;
		
	}
	public String insert() {
		domitoryDao = new DomitoryDao();
		
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		int count = domitoryDao.insert(query);
		
		if(count > 0) {
			this.updateSuccess("添加成功");
		} else {
			this.updateError("失败,服务器异常!");
		}
		
		return listAction;
	}
	
	public String update() {
		domitoryDao = new DomitoryDao();
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		if(domitoryDao.update(query) > 0) {
			this.updateSuccess("修改成功");
		} else {
			this.updateError("失败,服务器异常!");
		}
		
		return listAction;
	}
	
	public String edit() {
		domitoryDao = new DomitoryDao();
		DomitoryBean DomitoryBean = domitoryDao.getById(Integer.valueOf(getRequest().getParameter("domitoryId")));
		getRequest().setAttribute("model", DomitoryBean);
		return getJspPath("Edit.jsp");
	}
	
	public String deleteBatch() {
		domitoryDao = new DomitoryDao();
		List<String> domitoryIds = new ArrayList<String>();
		
		for(String item : items) {
			domitoryIds.add(item);
		}
		
		if(domitoryDao.deleteBatch(domitoryIds) > 0){
			this.updateSuccess("删除成功");
		} else {
			this.updateError("失败,服务器异常!");
		}
		return listAction;
	}
	
	public String list() {
		PageBean<DomitoryBean> pageBean = query.getPageBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode==null ? 1 : pageCode);
		domitoryDao = new DomitoryDao();
		query = domitoryDao.findPage(query);
			
		saveQuery(query);
		
		return getJspPath("List.jsp");
		
	}
	
	public void ajaxGetDomitorys() throws IOException {
		this.getReponse().setContentType("text/json");
		domitoryDao = new DomitoryDao();
		query = new DomitoryQuery();
		query.setBuildingId(Integer.valueOf(getRequest().getParameter("buildingId")));
		List<DomitoryBean> list = domitoryDao.findList(query);
		JSONObject jsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(DomitoryBean domitoryBean : list) {
			jsonObject.put("domitoryId",domitoryBean.getDomitoryId());
			jsonObject.put("domitoryCode",domitoryBean.getDomitoryCode());
			
			jsonArray.add(jsonObject);
			jsonObject.clear();
		}
		
		getReponse().getWriter().print(jsonArray.toString());
	}
	
	public DomitoryQuery getQuery() {
		return query;
	}

	public void setQuery(DomitoryQuery query) {
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
		query = new DomitoryQuery();
	}

	@Override
	public DomitoryQuery getModel() {
		return query;
	}

	
	public String toDomitoryList() {
		return getJspPath("List.jsp");
	}
	
	public String create() {
		return getJspPath("Insert.jsp");
		
	}
	
	
}
