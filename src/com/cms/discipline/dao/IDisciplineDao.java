package com.cms.discipline.dao;

import java.util.List;

import com.cms.discipline.bean.DisciplineBean;
import com.cms.discipline.model.DisciplineQuery;

public interface IDisciplineDao {

	public List<DisciplineBean> findList();
	
	public DisciplineQuery findPage(DisciplineQuery qyery);
	
	public int insert(DisciplineQuery query);
	
	public int deleteBatch(List<String> DisciplineIds);
	
	public int update(DisciplineQuery qyery);
	
	public DisciplineBean getById(String DisciplineId);

}
