package com.cms.discipline.bean;

import java.util.Date;

/**
 * @author tooyear
 *
 */
public class DisciplineBean {

	private Integer disciplineId;
	private Integer buildingId;
	private Integer domitoryId;
	private String disciplineContent;
	private Date disciplineDate;
	private Integer isCriticize;
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
	public Integer getDisciplineId() {
		return disciplineId;
	}
	public void setDisciplineId(Integer disciplineId) {
		this.disciplineId = disciplineId;
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
	public String getDisciplineContent() {
		return disciplineContent;
	}
	public void setDisciplineContent(String disciplineContent) {
		this.disciplineContent = disciplineContent;
	}
	public Date getDisciplineDate() {
		return disciplineDate;
	}
	public void setDisciplineDate(Date disciplineDate) {
		this.disciplineDate = disciplineDate;
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
		return "DisciplineBean [disciplineId=" + disciplineId + ", buildingId=" + buildingId + ", domitoryId="
				+ domitoryId + ", disciplineContent=" + disciplineContent + ", disciplineDate=" + disciplineDate
				+ ", isCriticize=" + isCriticize + ", createDate=" + createDate + ", createId=" + createId + "]";
	}

}
