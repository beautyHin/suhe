package com.cms.domitory.dao;


import java.util.List;

import com.cms.domitory.bean.DomitoryBean;
import com.cms.domitory.model.DomitoryQuery;



public interface IDomitoryDao {

	public List<DomitoryBean> findList(DomitoryQuery qyery);
	
	public DomitoryQuery findPage(DomitoryQuery qyery);
	
	public int insert(DomitoryQuery query);
	
	public int deleteBatch(List<String> DomitoryIds);
	
	public int update(DomitoryQuery qyery);
	
	public DomitoryBean getById(Integer DomitoryId);
	
	
}
