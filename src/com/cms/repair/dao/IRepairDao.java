package com.cms.repair.dao;

import java.util.List;

import com.cms.repair.bean.RepairBean;
import com.cms.repair.model.RepairQuery;

public interface IRepairDao {

	public List<RepairBean> findList();
	
	public RepairQuery findPage(RepairQuery qyery);
	
	public int insert(RepairQuery query);
	
	public int deleteBatch(List<String> repairIds);
	
	public int update(RepairQuery qyery);
	
	public RepairBean getById(Integer repairId);

}
