package com.cms.repair.action;

import java.util.Date;

import com.cms.repair.bean.RepairBean;
import com.cms.repair.dao.IRepairDao;
import com.cms.repair.dao.impl.RepairDao;
import com.cms.repair.model.RepairQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 寝室报修--宿舍管理员端
 *
 */
public class RepairAction extends BaseAction implements ModelDriven<RepairQuery>{

	private IRepairDao repairDao;
	private RepairQuery query;
	private Integer pageCode;
	private String listAction = "!/cms/teacher/repair/Repair/list.action";
	private String getJspUrl(String _jsp) {
		return "/jsp/cms/teacher/repair/repair"+_jsp;
	}
	
	public String list() {
		repairDao = new RepairDao();
		PageBean<RepairBean> pageBean = query.getPageBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode == null ? 1 : pageCode);
		query = repairDao.findPage(query);
		saveQuery(query);
		return getJspUrl("List.jsp");
	}

	public String update() {
		repairDao = new RepairDao();
		if(repairDao.update(query) > 0) {
			this.updateSuccess("维修记录修改成功!");
		} else {
			this.updateError("失败,服务器错误!");

		}
		return listAction;
		
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

	@Override
	public RepairQuery getModel() {
		if(query == null) {
			query = new RepairQuery();
		}
		return query;
	}
	
}
