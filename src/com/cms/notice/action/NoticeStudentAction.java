package com.cms.notice.action;

import com.cms.notice.bean.NoticeBean;
import com.cms.notice.dao.INoticeDao;
import com.cms.notice.dao.impl.NoticeDao;
import com.cms.notice.model.NoticeQuery;
import com.framework.common.BaseAction;
import com.framework.common.PageBean;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 宿舍公告学生身份
 *
 */
public class NoticeStudentAction extends BaseAction implements ModelDriven<NoticeQuery>  {

	
	private NoticeQuery query;
	private INoticeDao noticeDao;
	
	private Integer pageCode;
	/**
	 * 
	 * 查看宿舍公告
	 */
	public String list() {
		noticeDao = new NoticeDao();
		PageBean<NoticeBean> pageBean = query.getPageBean();
		pageBean.setpageCode(pageCode==null?1:pageCode);
		pageBean.setpageSize(10);
		query = noticeDao.findPage(query);
		saveQuery(query);
		return "/jsp/cms/student/noticeList.jsp";
	}
	public NoticeQuery getNoticeQuery() {
		return query;
	}
	public void setNoticeQuery(NoticeQuery noticeQuery) {
		this.query = noticeQuery;
	}
	
	public Integer getPageCode() {
		return pageCode;
	}
	public void setPageCode(Integer pageCode) {
		this.pageCode = pageCode;
	}
	@Override
	public NoticeQuery getModel() {
		if(query == null)
			query =  new NoticeQuery();
		return query;
	}
	
	

}
