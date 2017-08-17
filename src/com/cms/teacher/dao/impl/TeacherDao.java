package com.cms.teacher.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cms.teacher.bean.TeacherBean;
import com.cms.teacher.dao.ITeacherDao;
import com.cms.teacher.model.TeacherQuery;
import com.framework.common.BaseDao;
import com.framework.common.PageBean;

public class TeacherDao extends BaseDao implements ITeacherDao {

	private Connection con;
	
	
	public int listCount(TeacherQuery query) {
		Connection con = this.getConnection();
		String sql = ""; 
		
		//初始sql
		StringBuffer countSql = new StringBuffer("select count(*) from tTeacher"); 
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");

		countSql.append(whereSql);
		
		//拼接where
		PageBean<TeacherBean> pageBean = this.appendWhereSql(query, false); // 获得where
		sql = countSql.append(pageBean.getSql()).toString();
		
		// 打印sql 和 param
		System.out.println("SQL:"+sql);
		System.out.println("Param:"+pageBean.getSqlParam());
		
		// 查询
		QueryRunner queryRunner = new QueryRunner();
		Number count = 0;
		try {
			count =(Number)(queryRunner.query(con,sql,new ScalarHandler(),pageBean.getSqlParam().toArray()));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return count.intValue();
	}
	
	@Override
	public TeacherQuery list(TeacherQuery query) {
		
		//取出分页信息
		PageBean<TeacherBean> pageBean = query.getPageBean();
		
		//先查询总数用于分页
		int count = this.listCount(query);
		pageBean.settotalRecord(count);
		
		if(count > 0) { //正式查询数据
			StringBuffer sql = new StringBuffer("SELECT * FROM tteacher");
			
			StringBuffer whereSql = new StringBuffer(" where 1=1 ");
			sql.append(whereSql);
			QueryRunner queryRunner = new QueryRunner();
			con = this.getConnection();
			
			// 拼接where
			pageBean = this.appendWhereSql(query,true);
			sql.append(pageBean.getSql());
			
			// 打印sql 和 param
			System.out.println("SQL:"+sql);
			System.out.println("Param:"+pageBean.getSqlParam());
			
			List<TeacherBean> stuList = null;
			// 查询开始
			try {
				stuList = queryRunner.query(con,sql.toString(), new BeanListHandler<>(TeacherBean.class),pageBean.getSqlParam().toArray());
				pageBean.setDatas(stuList);
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.closeCon(con);
			}
		}

		return query;
		
	}

	
	
	@Override
	public int insert(TeacherQuery query) {
		Connection con = this.getConnection();
		int count = 0;
		String sql = "insert into tTeacher values(?,?,?,?,?,?,?)";
		Object[] params = this.beanToObject(query);
		QueryRunner queryRunner = new QueryRunner();
		
		System.out.println("SQL:"+sql);
		System.out.println("Params:"+params);
		
		try {
			count = queryRunner.update(con, sql, params);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			this.closeCon(con);
		}
		
		return count;
	}
	
	@Override
	public boolean update(TeacherQuery query) {
		StringBuffer sql = new StringBuffer("update tTeacher set");
		PageBean<TeacherBean> pageBean = new PageBean<TeacherBean>();
		pageBean = appendUpdateSql(query);
		int count = 0;
		sql.append(pageBean.getSql());
		
		System.out.println("SQL:"+sql.toString());
		System.out.println("param:"+pageBean.getSqlParam());
		
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		try {
			count = queryRunner.update(con, sql.toString(), pageBean.getSqlParam().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return count > 0? true : false;
	}
	
	private  PageBean<TeacherBean> appendUpdateSql(TeacherQuery query) {
		PageBean<TeacherBean> pageBean = new PageBean<TeacherBean>();
		List<Object> paramList = new ArrayList<Object>();
		
		StringBuffer contentSql = new StringBuffer();
		
		if(query.getTeacherName() != null) {
			contentSql.append(" teacherName = ?,");
			paramList.add(query.getTeacherName());
			
		}
		
		if(query.getSex() != null) {
			contentSql.append(" sex = ?,");
			paramList.add(query.getSex());
		}
		
		if(query.getPhone() != null) {
			contentSql.append(" phone = ?,");
			paramList.add(query.getPhone());
		}

		if(query.getManageBuiId() != null) {
			contentSql.append(" manageBuiId = ?,");
			paramList.add(query.getManageBuiId());
		}
		if(query.getCreateDate() != null) {
			contentSql.append(" createDate = ?,");
			paramList.add(query.getCreateDate());
		}
		
		if(query.getCreateId() != null) {
			contentSql.append(" createId = ?,");
			paramList.add(query.getCreateId());
		}

		contentSql.append("teacherId = teacherId");
		contentSql.append(" where teacherId = ?");
		paramList.add(query.getTeacherId());
		
		pageBean.setSql(contentSql.toString());
		pageBean.setSqlParam(paramList);
		
		return pageBean;
		
	}

	/**
	 * 拼接查询where
	 * 
	 * @return
	 */
	private PageBean<TeacherBean> appendWhereSql(TeacherQuery query,boolean isLimit) {
		
		List<Object> listParam = new ArrayList<Object>(); //保存查询条件
		// 拼装 where
		StringBuffer whereSql = new StringBuffer("");
		
		if(query.getTeacherId() != null && !query.getTeacherId().equals("")) {
			whereSql.append(" AND teacherId = ?");
			listParam.add(query.getTeacherId());
		}
		
		if(query.getTeacherName() != null && query.getTeacherName().trim().length() != 0) {
			whereSql.append(" AND teacherName like ?");
			listParam.add("%"+query.getTeacherName()+"%");
		}
		
		if(query.getSex() != null && !query.getSex().equals("")) {
			whereSql.append(" AND sex = ?");
			listParam.add(query.getSex());
		}
		
		
		if(query.getPhone() != null && query.getPhone().trim().length() != 0) {
			whereSql.append(" AND phone like ?");
			listParam.add("%"+query.getPhone()+"%");
		}
		if(query.getManageBuiId() != null && !query.getManageBuiId().equals("")) {
			whereSql.append(" AND manageBuiId = ?");
			listParam.add(query.getManageBuiId());
		}
		if(query.getCreateDate() != null && !query.getCreateDate().equals("")) {
			whereSql.append(" AND createDate = ?");
			listParam.add(query.getCreateDate());
		}
		if(query.getCreateId() != null && !query.getCreateId().equals("")) {
			whereSql.append(" AND createId = ?");
			listParam.add(query.getCreateId());
		}
		
		
		// 拼装where end

		PageBean<TeacherBean> pageBean = query.getPageBean();
		if(isLimit) {
		// 分页
			whereSql.append(" limit ?,?");
			listParam.add((pageBean.getpageCode()-1) * pageBean.getpageSize());
			listParam.add(pageBean.getpageSize());
		}
		pageBean.setSql(whereSql.toString());
		pageBean.setSqlParam(listParam);
		return pageBean;
	}
	
	@Override
	public boolean deletebatch(List<String> TeacherId) {
		QueryRunner qr = new QueryRunner();
		con = getConnection();
		Object[][] params = new Object[TeacherId.size()][];
		
		String sql = "delete from tTeacher where teacherId  = ?";
		
		for(int i=0; i<TeacherId.size(); ++i) {
			params[i] = new Object[]{TeacherId.get(i)};
			
		}
		int count = 0;
		
		System.out.println("SQL:"+sql);
		System.out.println("Param:");
		
		for(int i=0;i<params.length;++i) {
			for(int j=0;j<params[i].length; ++j) {
				System.out.print(params[i][j]+",");
				
			}
		}
		
		try {
			count = qr.batch(con, sql, params).length;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeCon(con);
		}
		
		return count > 0 ? true : false;
		
	}
	
	

	@Override
	public TeacherBean getById(Integer teacherId) {
		TeacherBean teahcerBean = new TeacherBean();
		String sql = "select * from tTeacher where teacherId = ?";
		
		System.out.println("SQL:"+sql);
		System.out.println("params:"+teacherId);
		
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		try {
			teahcerBean = queryRunner.query(con, sql, new BeanHandler<TeacherBean>(TeacherBean.class), teacherId);
		} catch (SQLException e) {
			teahcerBean = null;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		return teahcerBean;
	}

	/**
	 * 将bean转换为Object , 
	 * 请注意数组的存放顺序要与数据库字段的排列顺序一致
	 * @param query
	 * @return
	 */
	public Object[] beanToObject(TeacherQuery query) {
		//id stuCode name sex className buildingId dormitoryId isStay phone coachTel createDate createId

		Object[] obj = {
				query.getTeacherId(),
				query.getTeacherName(),
				query.getSex(),
				query.getPhone(),
				query.getManageBuiId(),
				query.getCreateDate(),
				query.getCreateId()
		};
		return obj;
		
		
	}

}
