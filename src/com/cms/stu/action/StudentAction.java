
package com.cms.stu.action;
/**
 * 学生管理模块--宿舍管理员端
 */
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

public class StudentAction extends BaseAction implements ModelDriven,Preparable {
	
	private static final long serialVersionUID = -8446539413216862193L;
	private IStudentDao studentDao;
	private StudentQuery query;
	private Integer pageCode;
	private String[] items;
	private String listAction = "!/cms/teacher/stu/Student/studentList.action";
	
	public String getJspUrl(String _jsp) {
		return "/jsp/cms/teacher/student/student"+_jsp;
	}
	
	public String studentList() {
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
	
	public String  batchStudentExit() {
		studentDao = new StudentDao();
	
		List<String> stuCode = new ArrayList<String>();
		if(items.length > 0 ) {
			for(String item : items) {
				stuCode.add(item);
			}
		}
		if(studentDao.studentExitbatch(stuCode)) {
			this.updateSuccess("迁出成功");
		} else {
			this.updateError("迁出失败");
		}
		
		return "!/cms/teacher/stu/Student/studentExitList.action";
	}
	
	public String updateDomitoryList() {
		PageBean<StudentBean> pageBean = query.getPegeBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode == null? 1 : pageCode);
		
		studentDao = new StudentDao();
		query.setIsStay(1);
		query = studentDao.studentListPage(query);
		saveQuery(query);
		
		return  getJspUrl("UpdateDomitoryList.jsp");
	}
	
	
	public String updateDomitoryEdit() {
		StudentDao studentDao = new StudentDao();
		StudentBean studentBean = studentDao.getById(query.getId());
		DomitoryDao domitoryDao = new DomitoryDao();
		DomitoryBean domitoryBean = domitoryDao.getById(studentBean.getDomitoryId());
		studentBean.setDomitoryCode(domitoryBean.getDomitoryCode());
		getRequest().setAttribute("model",studentBean);
		return getJspUrl("UpdateDomitory_edit.jsp");
	}
	
	public String updateDomitory() {
		StudentQuery oldQuery = new StudentQuery();
		//查询更新前的记录
		StudentQuery newquery = new StudentQuery();
		newquery.setId(query.getId());
		
		//更新的字段
		oldQuery = this.getOldBean(newquery);
		oldQuery.setBuildingId(query.getBuildingId());
		oldQuery.setDomitoryId(query.getDomitoryId());
		
		studentDao = new StudentDao();
		if(studentDao.update(oldQuery)) {
			this.updateSuccess("换寝成功");
		} else {
			this.updateError("服务器错误，请稍后重试");
		}
		
		
		return "!/cms/teacher/stu/Student/updateDomitoryList.action";
		
	}
	
	public StudentQuery getOldBean(StudentQuery query ) {
		studentDao = new StudentDao();
		query  = studentDao.findList(query);
		return query;
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
