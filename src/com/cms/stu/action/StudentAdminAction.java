package com.cms.stu.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cms.domitory.bean.DomitoryBean;
import com.cms.domitory.dao.impl.DomitoryDao;
import com.cms.stu.bean.StudentBean;
import com.cms.stu.dao.IStudentDao;
import com.cms.stu.dao.impl.StudentDao;
import com.cms.stu.model.StudentQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 学生信息管理--管理员端
 *
 */

public class StudentAdminAction extends BaseAction implements ModelDriven,Preparable {
	
	private IStudentDao studentDao;
	private StudentQuery query;
	private Integer pageCode;
	private String[] items;
	
	private String listAction = "!/cms/admin/stu/StudentAdmin/list.action";
	
	public String getJspUrl(String _jsp) {
		return "/jsp/cms/admin/student/student"+_jsp;
	}
	
	public String list() {
		// 设置分页
		PageBean<StudentBean> pageBean = query.getPegeBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode == null? 1 : pageCode);
		
		studentDao = new StudentDao();
		query = studentDao.studentListPage(query);
		saveQuery(query);
		
		return this.getJspUrl("List.jsp");
	}
	
	public String studentExitList() {
		PageBean<StudentBean> pageBean = query.getPegeBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode == null? 1 : pageCode);
		studentDao = new StudentDao();
		query.setIsStay(1); //在住的
		query = studentDao.studentListPage(query);
		saveQuery(query);
		
		return this.getJspUrl("Exit.jsp");
	}

	public String studentInsert() {
		studentDao = new StudentDao();
		query.setCreateDate(new Date());
		query.setCreateId(this.getUser().getId());
		query.setIsStay(1); //入住
		if(studentDao.studentInsert(query) > 0) {
			this.updateSuccess("添加成功");
		} else {
			this.updateError("添加失败");
		}
		return listAction;
		
	}
	
	public String  batchDelete() {
		studentDao = new StudentDao();
	
		List<String> studentId = new ArrayList<String>();
		if(items.length > 0 ) {
			for(String item : items) {
				studentId.add(item);
			}
		}
		if(studentDao.deletebatch(studentId)) {
			this.updateSuccess("删除成功");
		} else {
			this.updateError("删除失败");
		}
		
		return listAction;
	}
	
	public String edit() {
		
		studentDao = new StudentDao();
		StudentBean model =  studentDao.getById(query.getId());
		DomitoryDao domitoryDao = new DomitoryDao();
		DomitoryBean domitoryBean = domitoryDao.getById(model.getDomitoryId());
		model.setDomitoryCode(domitoryBean.getDomitoryCode());

		if(model == null) {
			this.updateError("服务器错误,请稍后尝试!");
			return listAction;
		}
		getRequest().setAttribute("model", model);
		return getJspUrl("Edit.jsp");
		
	}
	
	public String update() {
		studentDao = new StudentDao();
		
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		
		if(studentDao.update(query)) {
			this.updateSuccess("更新成功");
		} else {
			this.updateError("更新失败");
		}
		
		return  listAction;
		
		
		
	}
	
	public String toStudentInsert() {
		return this.getJspUrl("Insert.jsp");
	}
	
	public String toStudentExit() {
		return this.getJspUrl("Exit.jsp");
		
	}
	
	
	public Integer getPageCode() {
		return pageCode;
	}
	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}

	public StudentQuery getQuery() {
		return query;
	}

	public void setQuery(StudentQuery query) {
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
		query = new StudentQuery();
	}

	@Override
	public Object getModel() {
		return query;
	}
	
	
	

}

