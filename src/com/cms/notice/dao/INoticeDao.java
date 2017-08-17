package com.cms.notice.dao;

import java.util.List;

import com.cms.notice.bean.NoticeBean;
import com.cms.notice.model.NoticeQuery;

public interface INoticeDao {
	

	public List<NoticeBean> findList();
	
	public NoticeQuery findPage(NoticeQuery qyery);
	
	public int insert(NoticeQuery query);
	
	public int deleteBatch(List<String> NoticeIds);
	
	public int update(NoticeQuery qyery);
	
	public NoticeBean getById(String NoticeId);
	

}
