package com.cms.liveout.dao;

import java.util.List;

import com.cms.liveout.bean.LiveOutBean;
import com.cms.liveout.model.LiveOutQuery;

public interface ILiveOutDao {

	public List<LiveOutBean> findList();
	
	public LiveOutQuery findPage(LiveOutQuery qyery);
	
	public int insert(LiveOutQuery query);
	
	public int deleteBatch(List<String> LiveOutIds);
	
	public int update(LiveOutQuery qyery);
	
	public LiveOutBean getById(String LiveOutId);

}
