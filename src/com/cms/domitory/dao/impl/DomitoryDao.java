package com.cms.domitory.dao.impl;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cms.domitory.bean.DomitoryBean;
import com.cms.domitory.dao.IDomitoryDao;
import com.cms.domitory.model.DomitoryQuery;
import com.framework.common.BaseDao;
import com.framework.common.PageBean;

public class DomitoryDao extends BaseDao implements IDomitoryDao  {

	private Connection con;
	 
	
	public List<DomitoryBean> findList(DomitoryQuery query) {
		List<DomitoryBean> list =new ArrayList<DomitoryBean>();
		
		String sql = "SELECT * FROM tDomitory";
		PageBean<DomitoryBean> pageBean = appendWhereSql(query,false);
		sql += pageBean.getSql();
		
		System.out.println("SQL:"+sql);
		System.out.println("param:"+pageBean.getSqlParam());
		
		QueryRunner queryRunner = new QueryRunner();
		con = getConnection();
		
		try {
			list = queryRunner.query(con, sql,new BeanListHandler<>(DomitoryBean.class),pageBean.getSqlParam().toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int findCount(DomitoryQuery query) {
		
		Number count = 0;
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM tDomitory");
		
		PageBean<DomitoryBean> pageBean = this.appendWhereSql(query,false);
		sql.append(pageBean.getSql());
		
		QueryRunner queryRunner = new QueryRunner();
		con = getConnection();
		
		try {
			 count = (Number)queryRunner.query(con, sql.toString(), new ScalarHandler(),pageBean.getSqlParam().toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			closeCon(con);
		}
		
		return count.intValue();		
	}
	
	
	@Override
	public DomitoryQuery findPage(DomitoryQuery query) {
		List<DomitoryBean> resultList = new ArrayList<DomitoryBean>();
		StringBuffer sql = new StringBuffer("SELECT * FROM tDomitory ");
		PageBean<DomitoryBean> pageBean = query.getPageBean();
		
		int count = this.findCount(query);
		
		if(count > 0) {
			pageBean = this.appendWhereSql(query, true);
			sql.append(pageBean.getSql());
			
			System.out.println("SQL:"+sql);
			System.out.println("Param:"+pageBean.getSqlParam());
			
			con = getConnection();
			QueryRunner queryRunner = new QueryRunner();
			try {
				resultList = queryRunner.query(con, sql.toString(), new BeanListHandler<>(DomitoryBean.class), pageBean.getSqlParam().toArray());
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

	private PageBean<DomitoryBean> appendWhereSql(DomitoryQuery query,boolean isLimit) {
		
		StringBuffer whereSql = new StringBuffer(" where 1=1 "); 
		List<Object> listParam = new ArrayList<Object>();
		
		if(query.getBuildingId() != null && !query.getBuildingId().equals("")) {
			whereSql.append(" AND buildingId = ?");
			listParam.add(query.getBuildingId()); 
		}
		
		if(query.getDomitoryCode() !=null && !query.getDomitoryCode().equals("") ) {
			whereSql.append(" AND domitoryCode = ?");
			listParam.add(query.getDomitoryCode());
		}
		
		if(query.getDomitoryStatus() !=null && !query.getDomitoryStatus().equals("") ) {
			whereSql.append(" AND domitoryStatus = ?");
			listParam.add(query.getDomitoryStatus());
		}
		
		
		if(query.getDomitoryAsset() !=null && !query.getDomitoryAsset().equals("") ) {
			whereSql.append(" AND domitoryAsset = ?");
			listParam.add(query.getDomitoryAsset());
		}
		
		if(query.getCreateDate() !=null && !query.getCreateDate().equals("") ) {
			whereSql.append(" AND createDate = ?");
			listParam.add(query.getCreateDate());
		}
		
		
		if(query.getCreateId() !=null && !query.getCreateId().equals("") ) {
			whereSql.append(" AND createId = ?");
			listParam.add(query.getCreateId());
		}
		
		
		PageBean<DomitoryBean> pageBean = query.getPageBean();
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
	public int insert(DomitoryQuery query) {
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		List<Object> prarms = new ArrayList<Object>();
		prarms.add(query.getDomitoryId());
		prarms.add(query.getBuildingId());
		prarms.add(query.getDomitoryCode());
		prarms.add(query.getDomitoryType());
		prarms.add(query.getDomitoryStatus());
		prarms.add(query.getDomitoryAsset());
		prarms.add(query.getCreateDate());
		prarms.add(query.getCreateId());
		String sql = "insert into tDomitory values(?,?,?,?,?,?,?,?)";
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
	public int deleteBatch(List<String> DomitoryIds) {
		int count = 0;
		con = getConnection();
		String sql = "delete from tDomitory where DomitoryId = ?";
		Object[][] params = new Object[DomitoryIds.size()][];
		
		for(int i=0;i<DomitoryIds.size();++i) {
			params[i] = new Object[]{DomitoryIds.get(i)};
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
	public DomitoryBean getById(Integer domitoryId) {
		String sql = "select * from tDomitory where DomitoryId = ?";
		
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		DomitoryBean DomitoryBean = new DomitoryBean();
		try {
			DomitoryBean = queryRunner.query(con, sql, new BeanHandler<DomitoryBean>(DomitoryBean.class), domitoryId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return DomitoryBean;
	}

	@Override
	public int update(DomitoryQuery query) {
		int count = 0;
		StringBuffer sql = new StringBuffer("update tDomitory set ");
		
		query = appendUpdateSql(query);
		PageBean<DomitoryBean> pageBean = query.getPageBean();
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
	
	private DomitoryQuery  appendUpdateSql(DomitoryQuery query) {
		StringBuffer updateSql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		if(query.getDomitoryCode() != null) {
			updateSql.append(" DomitoryCode = ?,");
			params.add(query.getDomitoryCode());
		}
		
		if(query.getDomitoryType() != null) {
			updateSql.append(" DomitoryType = ?,");
			params.add(query.getDomitoryType());
		}
		
		if(query.getDomitoryStatus() != null) {
			updateSql.append(" DomitoryStatus = ?,");
			params.add(query.getDomitoryStatus());
		}
		if(query.getDomitoryAsset() != null) {
			updateSql.append(" DomitoryAsset = ?,");
			params.add(query.getDomitoryAsset());
		}
		
		if(query.getBuildingId() != null) {
			updateSql.append(" buildingId = ?,");
			params.add(query.getBuildingId());
		}
		
		
		if(query.getCreateDate() != null) {
			updateSql.append(" createDate = ?,");
			params.add(query.getCreateDate());
		}
		
		if(query.getCreateId() != null) {
			updateSql.append(" createId = ?,");
			params.add(query.getCreateId());
		}
		
		updateSql.append(" domitoryId = domitoryId");
		
		updateSql.append(" where domitoryId = ? ");
		params.add(query.getDomitoryId());
		
		query.getPageBean().setSql(updateSql.toString());
		query.getPageBean().setSqlParam(params);
		
		return query;
	}
	
	
}
