package com.cms.stu.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cms.stu.bean.StudentBean;
import com.cms.stu.dao.IStudentDao;
import com.cms.stu.model.StudentQuery;
import com.framework.common.BaseDao;
import com.framework.common.PageBean;

public class StudentDao extends BaseDao implements IStudentDao {

	private Connection con;
	
	
	public int studentListCount(StudentQuery query) {
		Connection con = this.getConnection();
		String sql = ""; 
		
		//初始sql
		StringBuffer countSql = new StringBuffer("select count(*) from tstudent s , tbuilding b , tdomitory d"); 
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");

		countSql.append(whereSql);
		
		//拼接where
		PageBean<StudentBean> pageBean = this.whereSql(query, false); // 获得where
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
	public StudentQuery studentListPage(StudentQuery query) {
		query = find(query, true);
		return query;
		
	}
	
	public StudentQuery findList(StudentQuery query) {
		query = find(query, false);
		return query;
	}

	
	private StudentQuery find(StudentQuery query,boolean limit) {
		//取出分页信息
		PageBean<StudentBean> pageBean = query.getPegeBean();
		
		//先查询总数用于分页
		int count = this.studentListCount(query);
		pageBean.settotalRecord(count);
		
		if(count > 0) { //正式查询数据
			StringBuffer sql = new StringBuffer("SELECT s.*,b.buildingName,d.domitoryCode FROM tstudent s , tbuilding b ,tdomitory d");
			
			StringBuffer whereSql = new StringBuffer(" where 1=1 ");
			
			sql.append(whereSql);
			
			QueryRunner queryRunner = new QueryRunner();
			con = this.getConnection();
			
			// 拼接where
			pageBean = this.whereSql(query,limit);
			sql.append(pageBean.getSql());
			
			// 打印sql 和 param
			System.out.println("SQL:"+sql);
			System.out.println("Param:"+pageBean.getSqlParam());
			
			List<StudentBean> stuList = null;
			// 查询开始
			try {
				stuList = queryRunner.query(con,sql.toString(), new BeanListHandler<>(StudentBean.class),pageBean.getSqlParam().toArray());
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
	public int studentInsert(StudentQuery query) {
		Connection con = this.getConnection();
		int count = 0;
		String sql = "insert into tstudent values(?,?,?,?,?,?,?,?,?,?,?,?)";
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
	public boolean update(StudentQuery query) {
		int count = 0;
		StringBuffer sql = new StringBuffer("update tstudent set");
		QueryRunner queryRunner = new QueryRunner();
		
		PageBean<StudentBean> pageBean = appendUpdateSql(query);
		sql.append(pageBean.getSql());
		
		System.out.println("SQL:"+sql.toString());
		System.out.println("params:"+pageBean.getSqlParam());
		
		con = getConnection();
		try {
			count = queryRunner.update(con, sql.toString(), pageBean.getSqlParam().toArray());
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}

		return count>0 ? true : false;
	}
	
	private  PageBean<StudentBean> appendUpdateSql(StudentQuery query) {
		PageBean<StudentBean> pageBean = new PageBean<StudentBean>();
		List<Object> paramList = new ArrayList<Object>();
		
		StringBuffer contentSql = new StringBuffer();
		
		if(query.getStuCode() != null) {
			 contentSql = new StringBuffer(" stuCode = ?,");
			 paramList.add(query.getStuCode());
		}
		if(query.getName() != null) {
			contentSql.append(" name = ?,");
			paramList.add(query.getName());
			
		}
		
		if(query.getSex() != null) {
			contentSql.append(" sex = ?,");
			paramList.add(query.getSex());
		}
		
		if(query.getClassName() != null) {
			contentSql.append(" className = ?,");
			paramList.add(query.getClassName());
		}

		if(query.getBuildingId() != null) {
			contentSql.append(" buildingId = ?,");
			paramList.add(query.getBuildingId());
		}
		
		if(query.getDomitoryId() != null) {
			contentSql.append(" DomitoryId = ?,");
			paramList.add(query.getDomitoryId());
		}
		
		if(query.getIsStay() != null) {
			contentSql.append(" isStay = ?,");
			paramList.add(query.getIsStay());
		}
		
		if(query.getPhone() != null) {
			contentSql.append(" phone = ?,");
			paramList.add(query.getPhone());
			
		}
		
		if(query.getCoachTel() != null) {
			contentSql.append(" coachTel = ?, ");
			paramList.add(query.getCoachTel());
		}
		
		if(query.getCreateDate() != null) {
			contentSql.append(" createDate = ?,");
			paramList.add(query.getCreateDate());
		}
		
		if(query.getCreateId() != null) {
			contentSql.append(" createId = ?,");
			paramList.add(query.getCreateId());
		}

		contentSql.append("id = id");
		contentSql.append(" where id = ?");
		paramList.add(query.getId());
		
		pageBean.setSql(contentSql.toString());
		pageBean.setSqlParam(paramList);
		
		return pageBean;
		
	}

	/**
	 * 拼接查询where
	 * 
	 * @return
	 */
	private PageBean<StudentBean> whereSql(StudentQuery query,boolean isLimit) {
		
		List<Object> listParam = new ArrayList<Object>(); //保存查询条件
		// 拼装 where
		StringBuffer whereSql = new StringBuffer("");
		whereSql.append(" AND s.buildingId = b.buildingId");
		whereSql.append(" AND s.DomitoryId = d.DomitoryId");
		if(query.getId() != null) {
			whereSql.append(" AND id = ?");
			listParam.add(query.getId());
		}
		
		if(query.getName() != null && query.getName().trim().length() != 0) {
			whereSql.append(" AND name like ?");
			listParam.add("%"+query.getName()+"%");
		}
		
		if(query.getStuCode() != null && query.getStuCode().trim().length() != 0) {
			whereSql.append(" AND stuCode like ?");
			listParam.add("%"+query.getStuCode()+"%");
		}
		if(query.getClassName() != null && query.getClassName().trim().length() != 0) {
			whereSql.append(" AND className = ?");
			listParam.add(query.getClassName());
		}
		if(query.getSex() != null) {
			whereSql.append(" AND sex = ?");
			listParam.add(query.getSex());
		}
		
		if(query.getIsStay() != null) {
			whereSql.append(" AND isStay = ?");
			listParam.add(query.getIsStay());
		}
		
		if(query.getBuildingId() != null) {
			whereSql.append("AND buildingId = ?");
			listParam.add(query.getBuildingId());
		}
		
		if(query.getDomitoryId() != null) {
			whereSql.append(" AND DomitoryId = ?");
		}
		
		// 拼装where end

		PageBean<StudentBean> pageBean = query.getPegeBean();
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
	public boolean studentExitbatch(List<String> stuCode) {
		QueryRunner qr = new QueryRunner();
		con = getConnection();
		Object[][] params = new Object[stuCode.size()][];
		
		String sql = "update tstudent set isStay = 0 where stuCode = ?"; //更新学生居住状态为迁出
		
		for(int i=0; i<stuCode.size(); ++i) {
			params[i] = new Object[]{stuCode.get(i)};
			
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
	public boolean deletebatch(List<String> studentId) {
		QueryRunner qr = new QueryRunner();
		con = getConnection();
		Object[][] params = new Object[studentId.size()][];
		
		String sql = "delete from tstudent where id = ?";
		
		for(int i=0; i<studentId.size(); ++i) {
			params[i] = new Object[]{studentId.get(i)};
			
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
	public StudentBean getById(Integer id) {
		String sql = "select * from tStudent where id = ?";
		
		StudentBean studentBean = new StudentBean();
		QueryRunner queryRunner = new QueryRunner();
		
		System.out.println("SQL:"+sql);
		System.out.println("params:"+id);
		
		con = getConnection();
		
		try {
			studentBean = queryRunner.query(con, sql, new BeanHandler<StudentBean>(StudentBean.class), id);
		} catch (SQLException e) {
			studentBean = null;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return studentBean;
	}

	/**
	 * 将bean转换为Object , 
	 * 请注意数组的存放顺序要与数据库字段的排列顺序一致
	 * @param query
	 * @return
	 */
	public Object[] beanToObject(StudentQuery query) {
		//id stuCode name sex className buildingId DomitoryId isStay phone coachTel createDate createId

		Object[] obj = {
				query.getId(),
				query.getStuCode(),
				query.getName(),
				query.getSex(),
				query.getClassName(),
				query.getBuildingId(),
				query.getDomitoryId(),
				query.getIsStay(),
				query.getPhone(),
				query.getCoachTel(),
				query.getCreateDate(),
				query.getCreateId()
		};
		return obj;
		
		
	}
	
}
