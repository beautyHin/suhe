package com.cms.domitory.bean;

import java.util.Date;

public class DomitoryBean {
	
	
	private Integer domitoryId;
	private Integer buildingId;
	private String domitoryCode;
	private Integer  domitoryType;
	private Integer  domitoryStatus;
	private String   domitoryAsset;
	private Date createDate;
	private Integer  createId;
	public Integer getDomitoryId() {
		return domitoryId;
	}
	public void setDomitoryId(Integer domitoryId) {
		this.domitoryId = domitoryId;
	}
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public String getDomitoryCode() {
		return domitoryCode;
	}
	public void setDomitoryCode(String domitoryCode) {
		this.domitoryCode = domitoryCode;
	}
	public Integer getDomitoryType() {
		return domitoryType;
	}
	public void setDomitoryType(Integer domitoryType) {
		this.domitoryType = domitoryType;
	}
	public Integer getDomitoryStatus() {
		return domitoryStatus;
	}
	public void setDomitoryStatus(Integer domitoryStatus) {
		this.domitoryStatus = domitoryStatus;
	}
	public String getDomitoryAsset() {
		return domitoryAsset;
	}
	public void setDomitoryAsset(String domitoryAsset) {
		this.domitoryAsset = domitoryAsset;
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
		return "DomitoryBean [domitoryId=" + domitoryId + ", buildingId=" + buildingId + ", domitoryCode="
				+ domitoryCode + ", domitoryType=" + domitoryType + ", domitoryStatus=" + domitoryStatus
				+ ", domitoryAsset=" + domitoryAsset + ", createDate=" + createDate + ", createId=" + createId + "]";
	}
	

}
