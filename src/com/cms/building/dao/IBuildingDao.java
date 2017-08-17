package com.cms.building.dao;

import java.util.List;
import java.util.Map;

import com.cms.building.bean.BuildingBean;
import com.cms.building.model.BuildingQuery;

public interface IBuildingDao {

	public List<BuildingBean> findList();
	
	public BuildingQuery findPage(BuildingQuery qyery);
	
	public int insert(BuildingQuery query);
	
	public int deleteBatch(List<String> buildingIds);
	
	public int update(BuildingQuery qyery);
	
	public BuildingBean getById(String buildingId);
	
	public Map<Integer , String> getBuildingName();
	
	
}
