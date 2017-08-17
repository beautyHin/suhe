package com.cms.discipline.action;

import com.cms.discipline.bean.DisciplineBean;
import com.cms.discipline.dao.IDisciplineDao;
import com.cms.discipline.dao.impl.DisciplineDao;
import com.cms.discipline.model.DisciplineQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author tooyear
 *
 */
public class DisciplineStudentAction extends BaseAction implements ModelDriven<DisciplineQuery>{

	private IDisciplineDao disciplineDao;
	private Integer pageCode;
	private DisciplineQuery query;
	
	public String list() {
		disciplineDao = new DisciplineDao();
		PageBean<DisciplineBean> pageBean  = query.getPageBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode == null ? 1 : pageCode);
		query.setIsCriticize(1);
		query = disciplineDao.findPage(query);
		saveQuery(query);
		return "/jsp/cms/student/disciplineList.jsp";
	}
	
	
	@Override
	public DisciplineQuery getModel() {
		if(query == null)
			query = new DisciplineQuery();
		return query;
	}

	public Integer getPageCode() {
		return pageCode;
	}

	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
	
}
