package com.cms.stu.bean;

import java.util.Date;


/**
 * 学生居住信息类(tstudent 表)
 *
 */
public class StudentBean {
	
	
	private Integer id;
	private String stuCode;
	private String name;
	private Integer sex;
	private String  className;
	private Integer buildingId;
	private Integer domitoryId;
	private Integer isStay;
	private String phone;
	private String coachTel;
	private Date createDate;
	private Integer createId;
	
	/*扩展其他bena的属性*/
	private String buildingName;
	private String domitoryCode;
	
	
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStuCode() {
		return stuCode;
	}
	public void setStuCode(String stuCode) {
		this.stuCode = stuCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
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
	public Integer getIsStay() {
		return isStay;
	}
	public void setIsStay(Integer isStay) {
		this.isStay = isStay;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCoachTel() {
		return coachTel;
	}
	public void setCoachTel(String coachTel) {
		this.coachTel = coachTel;
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
	
	public String getDomitoryCode() {
		return domitoryCode;
	}
	public void setDomitoryCode(String domitoryCode) {
		this.domitoryCode = domitoryCode;
	}
	@Override
	public String toString() {
		return "StudentBean [id=" + id + ", stuCode=" + stuCode + ", name=" + name + ", sex=" + sex + ", className="
				+ className + ", buildingId=" + buildingId + ", domitoryId=" + domitoryId + ", isStay=" + isStay
				+ ", phone=" + phone + ", coachTel=" + coachTel + ", createDate=" + createDate + ", createId="
				+ createId + ", buildingName=" + buildingName + ", domitoryCode=" + domitoryCode + "]";
	}
	
}
