package com.cms.stu.dao;

import java.util.List;

import com.cms.stu.bean.StudentBean;
import com.cms.stu.model.StudentQuery;

public interface IStudentDao {
	
	/**
	 *  查询学生的居住信息,分页
	 */
	public StudentQuery  studentListPage(StudentQuery query);
	
	/**
	 * 添加学生入住信息
	 */
	public int studentInsert(StudentQuery query);
	
	/**
	 * 根据学号批量修改学生迁出
	 * 
	 */
	public boolean studentExitbatch(List<String> studentCode);
	
	/**
	 * 更新居住信息
	 *
	 */
	public boolean update(StudentQuery query);
	
	/**
	 * 根据id批量删除
	 */
	public boolean deletebatch(List<String> studentId);
	
	/**
	 * 根据id获取学生信息
	 */
	public StudentBean getById(Integer id);
	
	public StudentQuery findList(StudentQuery query);

}
