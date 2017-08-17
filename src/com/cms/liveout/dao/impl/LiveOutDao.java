package com.cms.liveout.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cms.liveout.bean.LiveOutBean;
import com.cms.liveout.dao.ILiveOutDao;
import com.cms.liveout.model.LiveOutQuery;
import com.framework.common.BaseDao;
import com.framework.common.PageBean;

public class LiveOutDao extends BaseDao implements ILiveOutDao {
	
private Connection con;
	 
	
	public List<LiveOutBean> findList() {
		String sql = "SELECT * FROM tLiveOut";
		QueryRunner queryRunner = new QueryRunner();
		con = getConnection();
		List<LiveOutBean> list =new ArrayList<LiveOutBean>();
		try {
			list = queryRunner.query(con, sql,new BeanListHandler<>(LiveOutBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int findCount(LiveOutQuery query) {
		
		Number count = 0;
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM tLiveOut");
		
		PageBean<LiveOutBean> pageBean = this.appendWhereSql(query,false);
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
	public LiveOutQuery findPage(LiveOutQuery query) {
		List<LiveOutBean> resultList = new ArrayList<LiveOutBean>();
		StringBuffer sql = new StringBuffer("SELECT * FROM tLiveOut ");
		PageBean<LiveOutBean> pageBean = query.getPageBean();
		
		int count = this.findCount(query);
		
		if(count > 0) {
			pageBean = this.appendWhereSql(query, true);
			sql.append(pageBean.getSql());
			
			System.out.println("SQL:"+sql);
			System.out.println("Param:"+pageBean.getSqlParam());
			
			con = getConnection();
			QueryRunner queryRunner = new QueryRunner();
			try {
				resultList = queryRunner.query(con, sql.toString(), new BeanListHandler<>(LiveOutBean.class), pageBean.getSqlParam().toArray());
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

	private PageBean<LiveOutBean> appendWhereSql(LiveOutQuery query,boolean isLimit) {
		StringBuffer whereSql = new StringBuffer(" where 1=1 "); 
		List<Object> listParam = new ArrayList<Object>();
		
		if(query.getStuCode() != null && !query.getStuCode().equals("")) {
			whereSql.append(" AND stuCode = ?");
			listParam.add(query.getStuCode());
		}
		
		if(query.getStuName() != null && !query.getStuName().equals("")) {
			whereSql.append(" AND stuName = ?");
			listParam.add(query.getStuName());
		}
		
		if(query.getSex() != null && !query.getSex().equals("")) {
			whereSql.append(" AND sex = ?");
			listParam.add(query.getSex());
		}
		
		if(query.getLiveoutContent() != null && !query.getLiveoutContent().equals("") ) {
			whereSql.append(" AND liveoutContent = ?");
			listParam.add(query.getLiveoutContent());
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
		
		if(query.getLiveoutId() != null) {
			whereSql.append(" AND liveoutId = ?");
			listParam.add(query.getLiveoutId());
		}
		
		PageBean<LiveOutBean> pageBean = query.getPageBean();
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
	public int insert(LiveOutQuery query) {
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		List<Object> prarms = new ArrayList<Object>();
		
		prarms.add(query.getLiveoutId());
		prarms.add(query.getStuCode());
		prarms.add(query.getStuName());
		prarms.add(query.getSex());
		prarms.add(query.getLiveoutContent());
		prarms.add(query.getLiveoutDate());
		prarms.add(query.getIsCriticize());
		prarms.add(query.getCreateDate());
		prarms.add(query.getCreateId());
	
		String sql = "insert into tLiveOut values(?,?,?,?,?,?,?,?,?)";
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
	public int deleteBatch(List<String> LiveOutIds) {
		int count = 0;
		con = getConnection();
		String sql = "delete from tLiveOut where LiveOutId = ?";
		Object[][] params = new Object[LiveOutIds.size()][];
		
		for(int i=0;i<LiveOutIds.size();++i) {
			params[i] = new Object[]{LiveOutIds.get(i)};
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
	public LiveOutBean getById(String LiveOutId) {
		String sql = "select * from tLiveOut where LiveOutId = ?";
		
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		LiveOutBean LiveOutBean = new LiveOutBean();
		try {
			LiveOutBean = queryRunner.query(con, sql, new BeanHandler<LiveOutBean>(LiveOutBean.class), LiveOutId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return LiveOutBean;
	}

	@Override
	public int update(LiveOutQuery query) {
		int count = 0;
		StringBuffer sql = new StringBuffer("update tLiveOut set ");
		
		query = appendUpdateSql(query);
		PageBean<LiveOutBean> pageBean = query.getPageBean();
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
	
	private LiveOutQuery  appendUpdateSql(LiveOutQuery query) {
		StringBuffer updateSql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
				
		if(query.getStuCode() != null) {
			updateSql.append(" stuCode = ? ,");
			params.add(query.getStuCode());
		}
		
		if(query.getStuName() != null) {
			updateSql.append(" stuName = ? ,");
			params.add(query.getStuName());
		}
		
		if(query.getSex() != null) {
			updateSql.append(" sex = ? ,");
			params.add(query.getSex());
		}
		
		if(query.getLiveoutContent() != null) {
			updateSql.append(" liveoutContent = ? ,");
			params.add(query.getLiveoutContent());
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
		
		updateSql.append(" LiveOutId = LiveOutId");
		
		updateSql.append(" where LiveOutId = ? ");
		params.add(query.getLiveoutId());
		
		
		query.getPageBean().setSql(updateSql.toString());
		query.getPageBean().setSqlParam(params);
		
		return query;
	}
	

}
