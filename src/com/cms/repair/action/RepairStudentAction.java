package com.cms.repair.action;

import java.util.Date;

import com.cms.domitory.bean.DomitoryBean;
import com.cms.domitory.dao.impl.DomitoryDao;
import com.cms.repair.bean.RepairBean;
import com.cms.repair.dao.IRepairDao;
import com.cms.repair.dao.impl.RepairDao;
import com.cms.repair.model.RepairQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 寝室报修-学生端
 *
 */
public class RepairStudentAction extends BaseAction implements ModelDriven<RepairQuery>{

	private RepairQuery query;
	private IRepairDao repairDao;
	private Integer pageCode;
	
	private String getJspUrl(String _jsp) {
		return "/jsp/cms/student/repair/repair"+_jsp;
	}
	private String listAction = "!/cms/student/repair/RepairStudent/list.action";
	/**
	 * 查看当前用户创建的维修记录
	 * @return
	 */
	public String list() {
		repairDao = new RepairDao();
		PageBean<RepairBean> pageBean = query.getPageBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode == null ? 1 : pageCode);
		query.setCreateId(getUser().getId());
		repairDao.findPage(query);
		return getJspUrl("List.jsp");
	}
	
	/**
	 * 进入新增页面
	 */
	public String create() {
		return getJspUrl("Insert.jsp");
	}
	
	/**
	 * 添加维修记录
	 */
	public String insert() {
		repairDao = new RepairDao();
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		query.setStatus(0); //处理状态 进行中
		if(repairDao.insert(query) > 0) {
			this.updateSuccess("维修记录添加成功!");
		} else {
			this.updateError("失败,服务器错误!");
		}
		return listAction;
	}
	
	public String edit() {
		repairDao = new RepairDao();
		RepairBean model = repairDao.getById(query.getRepairId());
		DomitoryDao domitoryDao = new DomitoryDao();
		DomitoryBean domitoryBean = domitoryDao.getById(model.getDomitoryId());
		model.setDomitoryCode(domitoryBean.getDomitoryCode());
		getRequest().setAttribute("model", model);
		return getJspUrl("Edit.jsp");
	}
	
	public String update() {
		repairDao = new RepairDao();
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		if(repairDao.update(query) > 0) {
			this.updateSuccess("维修记录修改成功!");
		} else {
			this.updateError("失败,服务器错误!");

		}
		return listAction;
		
	}
	@Override
	public RepairQuery getModel() {
		if(query == null)
			query = new RepairQuery();
		return query;
	}

	public RepairQuery getQuery() {
		return query;
	}

	public void setQuery(RepairQuery query) {
		this.query = query;
	}

	public Integer getPageCode() {
		return pageCode;
	}

	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}

}
