package com.cms.building.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cms.building.bean.BuildingBean;
import com.cms.building.dao.IBuildingDao;
import com.cms.building.model.BuildingQuery;
import com.framework.common.BaseDao;
import com.framework.common.BuildingCache;
import com.framework.common.PageBean;

public class BuildingDao extends BaseDao implements IBuildingDao  {

	private Connection con;
	 
	
	public List<BuildingBean> findList() {
		String sql = "SELECT * FROM tbuilding";
		QueryRunner queryRunner = new QueryRunner();
		con = getConnection();
		List<BuildingBean> list =new ArrayList<BuildingBean>();
		try {
			list = queryRunner.query(con, sql,new BeanListHandler<>(BuildingBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int findCount(BuildingQuery query) {
		
		Number count = 0;
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM tbuilding");
		
		PageBean<BuildingBean> pageBean = this.appendWhereSql(query,false);
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
	public BuildingQuery findPage(BuildingQuery query) {
		List<BuildingBean> resultList = new ArrayList<BuildingBean>();
		StringBuffer sql = new StringBuffer("SELECT * FROM tbuilding ");
		PageBean<BuildingBean> pageBean = query.getPageBean();
		
		int count = this.findCount(query);
		
		if(count > 0) {
			pageBean = this.appendWhereSql(query, true);
			sql.append(pageBean.getSql());
			
			System.out.println("SQL:"+sql);
			System.out.println("Param:"+pageBean.getSqlParam());
			
			con = getConnection();
			QueryRunner queryRunner = new QueryRunner();
			try {
				resultList = queryRunner.query(con, sql.toString(), new BeanListHandler<>(BuildingBean.class), pageBean.getSqlParam().toArray());
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally {
				closeCon(con);
			}
			pageBean.setDatas(resultList);
			pageBean.settotalRecord(count);
		}
		
		return query;
	}

	private PageBean<BuildingBean> appendWhereSql(BuildingQuery query,boolean isLimit) {

		StringBuffer whereSql = new StringBuffer(" where 1=1 "); 
		List<Object> listParam = new ArrayList<Object>();
		if(query.getBuildingName() != null) {
			whereSql.append(" AND buildingName like ?");
			listParam.add("%"+query.getBuildingName()+"%"); 
		}
		
		if(query.getBuildingInfo() !=null ) {
			whereSql.append(" AND buildingInfo LIKE ?");
			listParam.add("%"+query.getBuildingInfo()+"%");
		}
		PageBean<BuildingBean> pageBean = query.getPageBean();
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
	public int insert(BuildingQuery query) {
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		List<Object> prarms = new ArrayList<Object>();
		prarms.add(query.getBuildingId());
		prarms.add(query.getBuildingName());
		prarms.add(query.getBuildingInfo());
		prarms.add(query.getCreateDate());
		prarms.add(query.getCreateId());
		String sql = "insert into tbuilding values(?,?,?,?,?)";
		int count = 0;

		System.out.println("SQL:"+sql);
		System.out.println("param:"+query);
		
		try {
			count = queryRunner.update(con, sql, prarms.toArray());
		
			BuildingCache.buildingMap = null;
			BuildingCache.buildingMap = this.getBuildingName();
		
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return count;
	}

	@Override
	public int deleteBatch(List<String> buildingIds) {
		int count = 0;
		con = getConnection();
		String sql = "delete from tbuilding where buildingId = ?";
		Object[][] params = new Object[buildingIds.size()][];
		
		for(int i=0;i<buildingIds.size();++i) {
			params[i] = new Object[]{buildingIds.get(i)};
		}
		System.out.println("SQL:"+sql);
		
		QueryRunner queryRunner = new QueryRunner();
		try {
			count = queryRunner.batch(con, sql, params).length;
			BuildingCache.buildingMap = null;
			BuildingCache.buildingMap = this.getBuildingName();
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return count;
	}

	
	
	@Override
	public BuildingBean getById(String buildingId) {
		String sql = "select * from tbuilding where buildingId = ?";
		
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		BuildingBean buildingBean = new BuildingBean();
		try {
			buildingBean = queryRunner.query(con, sql, new BeanHandler<BuildingBean>(BuildingBean.class), buildingId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return buildingBean;
	}

	@Override
	public int update(BuildingQuery query) {
		int count = 0;
		StringBuffer sql = new StringBuffer("update tbuilding set ");
		
		query = appendUpdateSql(query);
		PageBean<BuildingBean> pageBean = query.getPageBean();
		sql.append(pageBean.getSql());
		
		System.out.println("SQL:"+sql);
		System.out.println("param:"+pageBean.getSqlParam());

		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		try {
			count = queryRunner.update(con, sql.toString(), pageBean.getSqlParam().toArray());
			BuildingCache.buildingMap = null;
			BuildingCache.buildingMap = this.getBuildingName();
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return count;
	}
	
	private BuildingQuery  appendUpdateSql(BuildingQuery query) {
		StringBuffer updateSql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(query.getBuildingName() != null) {
			updateSql.append(" buildingName = ?,");
			params.add(query.getBuildingName());
		}
		
		if(query.getBuildingInfo() != null) {
			updateSql.append(" buildingInfo = ?,");
			params.add(query.getBuildingInfo());
		}
		
		if(query.getCreateDate() != null) {
			updateSql.append(" createDate = ?,");
			params.add(query.getCreateDate());
		}
		
		if(query.getCreateId() != null) {
			updateSql.append(" createId = ?,");
			params.add(query.getCreateId());
		}
		
		updateSql.append(" buildingId = buildingId");
		
		updateSql.append(" where buildingId = ? ");
		params.add(query.getBuildingId());
		
		query.getPageBean().setSql(updateSql.toString());
		query.getPageBean().setSqlParam(params);
		
		return query;
	}

	@Override
	public Map<Integer, String> getBuildingName() {
		List<BuildingBean>  list = findList();
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(BuildingBean buidling : list) {
			map.put(buidling.getBuildingId(), buidling.getBuildingName());
		}
		return map;
	}
	
	
	
	
}
