package com.cms.teacher.action;
/**
 * 宿舍管理员信息模块--管理员端
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cms.teacher.bean.TeacherBean;
import com.cms.teacher.dao.ITeacherDao;
import com.cms.teacher.dao.impl.TeacherDao;
import com.cms.teacher.model.TeacherQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class TeacherAdminAction extends BaseAction implements ModelDriven,Preparable {
	
	private ITeacherDao teacherDao;
	private TeacherQuery query;
	private Integer pageCode;
	private String[] items;
	
	private String pageCodeStr;
	
	private String listAction = "!/cms/admin/teacher/TeacherAdmin/list.action";
	public String getPageCodeStr() {
		return pageCodeStr;
	}
	public void setPageCodeStr(String pageCodeStr) {
		this.pageCodeStr = pageCodeStr;
	}
	
	public String getJspUrl(String _jsp) {
		return "/jsp/cms/admin/teacher/teacher"+_jsp;
	}
	
	public String list() {
		// 设置分页
		PageBean<TeacherBean> pageBean = query.getPageBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode == null? 1 : pageCode);
		
		teacherDao = new TeacherDao();
		query = teacherDao.list(query);
		saveQuery(query);
		
		return this.getJspUrl("List.jsp");
	}
	

	public String insert() {
		teacherDao = new TeacherDao();
		query.setCreateDate(new Date());
		query.setCreateId(this.getUser().getId());
		if(teacherDao.insert(query) > 0) {
			this.updateSuccess("添加成功");
		} else {
			this.updateError("添加失败");
		}
		return listAction;
		
	}
	
	public String edit() {
		teacherDao = new TeacherDao();	
		TeacherBean teacherBean =teacherDao.getById(query.getTeacherId());
		if(teacherBean == null) {

			this.updateError("服务器出错,请稍后重试");
			return listAction;
			
		}
		getRequest().setAttribute("model", teacherBean);
		return getJspUrl("Edit.jsp");
		
	}
	
	public String update() {
		teacherDao = new TeacherDao();
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		if(teacherDao.update(query)){
			this.updateSuccess("更新成功!");
		} else {
			this.updateError("失败,请稍后重试");
		}
		return listAction;
	}
	
	public String  batchDelete() {
		teacherDao = new TeacherDao();
	
		List<String> teacherId = new ArrayList<String>();
		if(items.length > 0 ) {
			for(String item : items) {
				teacherId.add(item);
			}
		}
		if(teacherDao.deletebatch(teacherId)) {
			this.updateSuccess("删除成功");
		} else {
			this.updateError("删除失败");
		}
		
		return listAction;
	}
	
	public String toTeacherInsert() {
		return this.getJspUrl("Insert.jsp");
	}
	
	public String toTeacherExit() {
		return this.getJspUrl("Exit.jsp");
		
	}
	
	public Integer getPageCode() {
		return pageCode;
	}
	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}

	public TeacherQuery getQuery() {
		return query;
	}

	public void setQuery(TeacherQuery query) {
		this.query = query;
	}
	
	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	@Override
	public void prepare() throws Exception {
		query = new TeacherQuery();
	}

	@Override
	public Object getModel() {
		return query;
	}
	
	
	

}
