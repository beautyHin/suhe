package com.cms.discipline.action;
/**
 * 违规模块--宿舍管理员端
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cms.discipline.bean.DisciplineBean;
import com.cms.discipline.dao.IDisciplineDao;
import com.cms.discipline.dao.impl.DisciplineDao;
import com.cms.discipline.model.DisciplineQuery;
import com.cms.domitory.bean.DomitoryBean;
import com.cms.domitory.dao.IDomitoryDao;
import com.cms.domitory.dao.impl.DomitoryDao;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class DisciplineAction extends BaseAction implements ModelDriven<DisciplineQuery>,Preparable {

	private static final long serialVersionUID = -7833369100883365549L;
	
	private DisciplineQuery query;
	private IDisciplineDao DisciplineDao;

	private String[] items;
	private Integer pageCode;
	private String listAction = "!/cms/teacher/discipline/Discipline/list.action"; 
	public String getJspPath(String _jsp) {
		
		return "/jsp/cms/teacher/discipline/discipline"+_jsp;
		
	}
	public String insert() {
		DisciplineDao = new DisciplineDao();
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		int count = DisciplineDao.insert(query);

		if(count > 0) {
			this.updateSuccess("添加成功");

		} else {
			this.updateSuccess("失败,服务器出错!");
		}
		
		return listAction;
	}
	
	public String update() {
		DisciplineDao = new DisciplineDao();
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		if(DisciplineDao.update(query) > 0) {
			this.updateSuccess("修改成功");

		} else {
			this.updateSuccess("失败,服务器出错!");
		}
		
		return listAction;
	}
	
	public String edit() {
		DisciplineDao = new DisciplineDao();
		DisciplineBean disciplineBean = DisciplineDao.getById(getRequest().getParameter("DisciplineId"));

		IDomitoryDao domitoryDao = new DomitoryDao();
		DomitoryBean domitoryBean = domitoryDao.getById(disciplineBean.getDomitoryId());
		disciplineBean.setDomitoryCode(domitoryBean.getDomitoryCode());
		getRequest().setAttribute("model", disciplineBean);
		return getJspPath("Edit.jsp");
	}
	
	public String deleteBatch() {
		DisciplineDao = new DisciplineDao();
		List<String> DisciplineIds = new ArrayList<String>();
		
		System.out.println(items.length);
		
		for(String item : items) {
			DisciplineIds.add(item);
		}
		
		if(DisciplineDao.deleteBatch(DisciplineIds) > 0){
			this.updateSuccess("删除成功");

		} else {
			this.updateSuccess("失败,服务器出错!");
		}
		return listAction;
	}
	
	public String list() {
		PageBean<DisciplineBean> pageBean = query.getPageBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode==null ? 1 : pageCode);
		DisciplineDao = new DisciplineDao();
		query = DisciplineDao.findPage(query);
		
		saveQuery(query);
		return getJspPath("List.jsp");
		
	}

	public DisciplineQuery getQuery() {
		return query;
	}
	public void setQuery(DisciplineQuery query) {
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
		query = new DisciplineQuery();
	}

	@Override
	public DisciplineQuery getModel() {
		return query;
	}

	
	public String toDisciplineList() {
		return getJspPath("List.jsp");
	}
	
	public String toDisciplineInsert() {
		return getJspPath("Insert.jsp");
		
	}
}
