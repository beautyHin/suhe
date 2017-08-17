package com.cms.building.bean;

import java.util.Date;


public class BuildingBean {

	private Integer buildingId;
	
	private String buildingName;
	
	private String buildingInfo;
	
	private Date createDate;
	
	private Integer createId;

	public Integer getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingInfo() {
		return buildingInfo;
	}

	public void setBuildingInfo(String buildingInfo) {
		this.buildingInfo = buildingInfo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	@Override
	public String toString() {
		return "BuildingBean [buildingId=" + buildingId + ", buildingName=" + buildingName + ", buildingInfo="
				+ buildingInfo + ", createDate=" + createDate + ", createId=" + createId + "]";
	}
	
}
