package com.cms.teacher.bean;

import java.util.Date;

public class TeacherBean {

	private Integer teacherId;
	private String teacherName;
	private Integer sex;
	private String phone;
	private Integer manageBuiId;
	private Date createDate;
	private Integer createId;
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getManageBuiId() {
		return manageBuiId;
	}
	public void setManageBuiId(Integer manageBuiId) {
		this.manageBuiId = manageBuiId;
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
		return "TeacherBean [teacherId=" + teacherId + ", teacherName=" + teacherName + ", sex=" + sex + ", phone="
				+ phone + ", manageBuiId=" + manageBuiId + ", createDate=" + createDate + ", createId=" + createId
				+ "]";
	}
	
	

	
}
