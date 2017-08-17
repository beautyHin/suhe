package com.cms.teacher.dao;

import java.util.List;

import com.cms.teacher.bean.TeacherBean;
import com.cms.teacher.model.TeacherQuery;



public interface ITeacherDao {
	
	public TeacherQuery  list(TeacherQuery query);

	public int insert(TeacherQuery query);

	public boolean update(TeacherQuery query);
	
	public boolean deletebatch(List<String> TeacherId);
	
	public TeacherBean getById(Integer teacherId);
}
