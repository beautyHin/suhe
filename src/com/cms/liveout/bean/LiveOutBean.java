package com.cms.liveout.bean;

import java.util.Date;

public class LiveOutBean {
	
	private Integer liveoutId;
	private String stuCode;
	private String stuName;
	private Integer sex;
	private String liveoutContent;
	private Date liveoutDate;
	private Integer isCriticize;
	private Date createDate;

	private Integer createId;
	public Integer getLiveoutId() {
		return liveoutId;
	}
	public void setLiveoutId(Integer liveoutId) {
		this.liveoutId = liveoutId;
	}
	public String getStuCode() {
		return stuCode;
	}
	public void setStuCode(String stuCode) {
		this.stuCode = stuCode;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getLiveoutContent() {
		return liveoutContent;
	}
	public void setLiveoutContent(String liveoutContent) {
		this.liveoutContent = liveoutContent;
	}
	public Date getLiveoutDate() {
		return liveoutDate;
	}
	public void setLiveoutDate(Date liveoutDate) {
		this.liveoutDate = liveoutDate;
	}
	public Integer getIsCriticize() {
		return isCriticize;
	}
	public void setIsCriticize(Integer isCriticize) {
		this.isCriticize = isCriticize;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date bcreateDate) {
		this.createDate = bcreateDate;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	@Override
	public String toString() {
		return "LiveOutBean [liveoutId=" + liveoutId + ", stuCode=" + stuCode + ", stuName=" + stuName + ", sex=" + sex
				+ ", liveoutContent=" + liveoutContent + ", liveoutDate=" + liveoutDate + ", isCriticize=" + isCriticize
				+ ", createDate=" + createDate + ", createId=" + createId + "]";
	}

}
