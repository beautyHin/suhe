package com.cms.repair.bean;

import java.util.Date;

public class RepairBean {

	private Integer repairId;
	private Integer buildingId;
	private Integer domitoryId;
	private String repairContent;
	private String phone;
	private Integer status;
	private Date createDate;
	private Integer createId;
	
	//扩展其他表的字段
	private String domitoryCode;
	
	public String getDomitoryCode() {
		return domitoryCode;
	}
	public void setDomitoryCode(String domitoryCode) {
		this.domitoryCode = domitoryCode;
	}
	public Integer getRepairId() {
		return repairId;
	}
	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public Integer getDomitoryId() {
		return domitoryId;
	}
	public void setDomitoryId(Integer domitoryId) {
		this.domitoryId = domitoryId;
	}
	public String getRepairContent() {
		return repairContent;
	}
	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
		return "RepairBean [repairId=" + repairId + ", buildingId=" + buildingId + ", domitoryId=" + domitoryId
				+ ", repairContent=" + repairContent + ", phone=" + phone + ", status=" + status + ", createDate="
				+ createDate + ", createId=" + createId + "]";
	}
	
}
