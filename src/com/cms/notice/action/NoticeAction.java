package com.cms.notice.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cms.notice.bean.NoticeBean;
import com.cms.notice.dao.INoticeDao;
import com.cms.notice.dao.impl.NoticeDao;
import com.cms.notice.model.NoticeQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class NoticeAction extends BaseAction implements ModelDriven,Preparable {

	private NoticeQuery query;
	private INoticeDao noticeDao;

	private String[] items;
	private Integer pageCode;
	private String listAction = "!/cms/teacher/notice/Notice/list.action";
	public String getJspPath(String _jsp) {
		
		return "/jsp/cms/teacher/notice/notice"+_jsp;
		
	}
	public String insert() {
		noticeDao = new NoticeDao();
		
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		int count = noticeDao.insert(query);
		
		if(count > 0) {
			this.updateSuccess("添加成功");
		} else {
			this.updateError("失败,服务器异常!");
		}
		
		return listAction;
	}
	
	public String update() {
		noticeDao = new NoticeDao();
		query.setCreateDate(new Date());
		query.setCreateId(getUser().getId());
		if(noticeDao.update(query) > 0) {
			this.updateSuccess("修改成功");
		} else {
			this.updateError("失败,服务器异常!");
		}
		
		return listAction;
	}
	
	public String edit() {
		noticeDao = new NoticeDao();
		NoticeBean noticeBean = noticeDao.getById(getRequest().getParameter("noticeId"));
		getRequest().setAttribute("model", noticeBean);
		return getJspPath("Edit.jsp");
	}
	
	public String deleteBatch() {
		noticeDao = new NoticeDao();
		List<String> noticeIds = new ArrayList<String>();
		for(String item : items) {
			noticeIds.add(item);
		}
		
		if(noticeDao.deleteBatch(noticeIds) > 0){
			this.updateSuccess("删除成功");
		} else {
			this.updateError("失败,服务器异常!");
		}
		return listAction;
	}
	
	public String list() {
		PageBean<NoticeBean> pageBean = query.getPageBean();
		pageBean.setpageSize(10);
		pageBean.setpageCode(pageCode==null ? 1 : pageCode);
		noticeDao = new NoticeDao();
		query = noticeDao.findPage(query);
			
		saveQuery(query);
		
		return getJspPath("List.jsp");
		
	}
	
	public NoticeQuery getQuery() {
		return query;
	}

	public void setQuery(NoticeQuery query) {
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
		query = new NoticeQuery();
	}

	@Override
	public Object getModel() {
		return query;
	}

	
	public String tonoticeList() {
		return getJspPath("List.jsp");
	}
	
	public String create() {
		return getJspPath("Insert.jsp");
		
	}
	
	
}
