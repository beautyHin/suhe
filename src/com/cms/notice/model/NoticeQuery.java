package com.cms.notice.model;

import com.cms.notice.bean.NoticeBean;
import com.framework.common.PageBean;

public class NoticeQuery  extends NoticeBean{

	private PageBean<NoticeBean> pageBean = new PageBean<NoticeBean>();

	public PageBean<NoticeBean> getPageBean() {
		return pageBean;
	}
	
}
