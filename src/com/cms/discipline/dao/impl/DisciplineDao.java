package com.cms.discipline.dao.impl;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cms.discipline.bean.DisciplineBean;
import com.cms.discipline.dao.IDisciplineDao;
import com.cms.discipline.model.DisciplineQuery;
import com.framework.common.BaseDao;
import com.framework.common.PageBean;

public class DisciplineDao extends BaseDao implements IDisciplineDao {
	
private Connection con;
	 
	
	public List<DisciplineBean> findList() {
		String sql = "SELECT dis.*,dom.domitoryCode FROM tDiscipline dis ,tdomitory dom where dis.domitoryId = dom.domitoryId";
		QueryRunner queryRunner = new QueryRunner();
		con = getConnection();
		List<DisciplineBean> list =new ArrayList<DisciplineBean>();
		try {
			list = queryRunner.query(con, sql,new BeanListHandler<>(DisciplineBean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int findCount(DisciplineQuery query) {
		
		Number count = 0;
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM tDiscipline dis ,tdomitory dom");
		
		PageBean<DisciplineBean> pageBean = this.appendWhereSql(query,false);
		sql.append(pageBean.getSql());
		
		QueryRunner queryRunner = new QueryRunner();
		con = getConnection();
		
		System.out.println("SQL:"+sql);
		System.out.println("Param:"+pageBean.getSqlParam());
		
		try {
			 count = (Number)queryRunner.query(con, sql.toString(), new ScalarHandler(),pageBean.getSqlParam().toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			closeCon(con);
		}
		
		return count.intValue();		
	}
	
	
	@Override
	public DisciplineQuery findPage(DisciplineQuery query) {
		List<DisciplineBean> resultList = new ArrayList<DisciplineBean>();
		StringBuffer sql = new StringBuffer("SELECT dis.*,dom.domitoryCode FROM tDiscipline dis, tdomitory dom ");
		PageBean<DisciplineBean> pageBean = query.getPageBean();
		
		int count = this.findCount(query);
		
		if(count > 0) {
			pageBean = this.appendWhereSql(query, true);
			sql.append(pageBean.getSql());
			
			System.out.println("SQL:"+sql);
			System.out.println("Param:"+pageBean.getSqlParam());
			
			con = getConnection();
			QueryRunner queryRunner = new QueryRunner();
			try {
				resultList = queryRunner.query(con, sql.toString(), new BeanListHandler<>(DisciplineBean.class), pageBean.getSqlParam().toArray());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  finally {
				closeCon(con);
			}
			pageBean.setDatas(resultList);
			pageBean.settotalRecord(count);
		}
		
		return query;
	}

	private PageBean<DisciplineBean> appendWhereSql(DisciplineQuery query,boolean isLimit) {
		StringBuffer whereSql = new StringBuffer(" where 1=1 "); 
		List<Object> listParam = new ArrayList<Object>();
		whereSql.append("and dis.domitoryId = dom.domitoryId");
		if(query.getBuildingId() != null && !query.getBuildingId().equals("")) {
			whereSql.append(" AND buildingId = ?");
			listParam.add(query.getBuildingId());
		}
		
		if(query.getDomitoryId() != null && !query.getDomitoryId().equals("")) {
			whereSql.append(" AND domitoryId = ?");
			listParam.add(query.getDomitoryId());
		}
		
		if(query.getDisciplineContent() != null && !query.getDisciplineContent().equals("")) {
			whereSql.append(" AND disciplineContent = ?");
			listParam.add(query.getDisciplineContent());
		}
		if(query.getDisciplineDate() != null && !query.getDisciplineDate().equals("")) {
			whereSql.append(" AND disciplineDate = ?");
			listParam.add(query.getDisciplineDate());
		}
		
		if(query.getIsCriticize() != null && !query.getIsCriticize().equals("")) {
			whereSql.append(" AND isCriticize = ?");
			listParam.add(query.getIsCriticize());
		}
		if(query.getCreateDate() != null  && !query.getCreateDate().equals("")) {
			whereSql.append(" AND createDate = ?");
			listParam.add(query.getCreateDate());
		}
		if(query.getCreateId() != null  && !query.getCreateId().equals("")) {
			whereSql.append(" AND createId = ?");
			listParam.add(query.getCreateId());
		}
		
		if(query.getDisciplineId() != null) {
			whereSql.append(" AND DisciplineId = ?");
			listParam.add(query.getDisciplineId());
		}
		
		PageBean<DisciplineBean> pageBean = query.getPageBean();
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
	public int insert(DisciplineQuery query) {
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		List<Object> prarms = new ArrayList<Object>();
		
		prarms.add(query.getDisciplineId());
		prarms.add(query.getBuildingId());
		prarms.add(query.getDomitoryId());
		prarms.add(query.getDisciplineContent());
		prarms.add(query.getDisciplineDate());
		prarms.add(query.getIsCriticize());
		prarms.add(query.getCreateDate());
		prarms.add(query.getCreateId());
	
		String sql = "insert into tDiscipline values(?,?,?,?,?,?,?,?)";
		int count = 0;

		System.out.println("SQL:"+sql);
		System.out.println("param:"+query);
		
		try {
			count = queryRunner.update(con, sql, prarms.toArray());
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return count;
	}

	@Override
	public int deleteBatch(List<String> DisciplineIds) {
		int count = 0;
		con = getConnection();
		String sql = "delete from tDiscipline where DisciplineId = ?";
		Object[][] params = new Object[DisciplineIds.size()][];
		
		for(int i=0;i<DisciplineIds.size();++i) {
			params[i] = new Object[]{DisciplineIds.get(i)};
		}
		System.out.println("SQL:"+sql);
		
		QueryRunner queryRunner = new QueryRunner();
		try {
			count = queryRunner.batch(con, sql, params).length;
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return count;
	}
	
	@Override
	public DisciplineBean getById(String DisciplineId) {
		String sql = "select * from tDiscipline where DisciplineId = ?";
		
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		System.out.println("SQL:"+sql);
		System.out.println("param:"+DisciplineId);
		DisciplineBean DisciplineBean = new DisciplineBean();
		try {
			DisciplineBean = queryRunner.query(con, sql, new BeanHandler<DisciplineBean>(DisciplineBean.class), DisciplineId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return DisciplineBean;
	}

	@Override
	public int update(DisciplineQuery query) {
		int count = 0;
		StringBuffer sql = new StringBuffer("update tDiscipline set ");
		
		query = appendUpdateSql(query);
		PageBean<DisciplineBean> pageBean = query.getPageBean();
		sql.append(pageBean.getSql());
		
		System.out.println("SQL:"+sql);
		System.out.println("param:"+pageBean.getSqlParam());

		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		try {
			count = queryRunner.update(con, sql.toString(), pageBean.getSqlParam().toArray());
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return count;
	}
	
	private DisciplineQuery  appendUpdateSql(DisciplineQuery query) {
		StringBuffer updateSql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
/*		disciplineId
		buildingId
		domitoryId
		disciplineContent
		disciplineDate
		isCriticize
		createDate
		createId
*/
		
		if(query.getBuildingId() != null) {
			updateSql.append(" buildingId = ? ,");
			params.add(query.getBuildingId());
		}
		
		if(query.getDomitoryId() != null) {
			updateSql.append(" domitoryId = ? ,");
			params.add(query.getDomitoryId());
		}
		
		if(query.getDisciplineDate() != null) {
			updateSql.append(" disciplineDate = ? ,");
			params.add(query.getDisciplineDate());
		}
		
		if(query.getDisciplineContent() != null) {
			updateSql.append(" disciplineContent = ? ,");
			params.add(query.getDisciplineContent());
		}
		
		if(query.getIsCriticize() != null) {
			updateSql.append(" isCriticize = ? ,");
			params.add(query.getIsCriticize());
			
		}
		
		if(query.getCreateDate() != null) {
			updateSql.append(" createDate = ?,");
			params.add(query.getCreateDate());
		}
		
		if(query.getCreateId() != null) {
			updateSql.append(" createId = ?,");
			params.add(query.getCreateId());
		}
		
		updateSql.append(" disciplineId = disciplineId");
		
		updateSql.append(" where disciplineId = ? ");
		params.add(query.getDisciplineId());
		
		
		query.getPageBean().setSql(updateSql.toString());
		query.getPageBean().setSqlParam(params);
		
		return query;
	}
	

}
