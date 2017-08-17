package com.cms.repair.dao.impl;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cms.repair.bean.RepairBean;
import com.cms.repair.dao.IRepairDao;
import com.cms.repair.model.RepairQuery;
import com.framework.common.BaseDao;
import com.framework.common.PageBean;

public class RepairDao extends BaseDao implements IRepairDao {
	
private Connection con;
	 
	
	public List<RepairBean> findList() {
		String sql = "SELECT r.*,dom.domitoryCode FROM tRepair r , tdomitory dom where r.domitoryId = dom.domitoryId";
		QueryRunner queryRunner = new QueryRunner();
		con = getConnection();
		List<RepairBean> list =new ArrayList<RepairBean>();
		try {
			list = queryRunner.query(con, sql,new BeanListHandler<>(RepairBean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int findCount(RepairQuery query) {
		
		Number count = 0;
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM tRepair r , tdomitory dom");
		
		PageBean<RepairBean> pageBean = this.appendWhereSql(query,false);
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
	public RepairQuery findPage(RepairQuery query) {
		List<RepairBean> resultList = new ArrayList<RepairBean>();
		StringBuffer sql = new StringBuffer("SELECT r.* ,dom.domitoryCode FROM tRepair r , tdomitory dom ");
		PageBean<RepairBean> pageBean = query.getPageBean();
		
		int count = this.findCount(query);
		
		if(count > 0) {
			pageBean = this.appendWhereSql(query, true);
			sql.append(pageBean.getSql());
			
			System.out.println("SQL:"+sql);
			System.out.println("Param:"+pageBean.getSqlParam());
			
			con = getConnection();
			QueryRunner queryRunner = new QueryRunner();
			try {
				resultList = queryRunner.query(con, sql.toString(), new BeanListHandler<>(RepairBean.class), pageBean.getSqlParam().toArray());
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

	private PageBean<RepairBean> appendWhereSql(RepairQuery query,boolean isLimit) {
		StringBuffer whereSql = new StringBuffer(" where 1=1 "); 
		List<Object> listParam = new ArrayList<Object>();
		whereSql.append("AND r.domitoryId = dom.domitoryId");
		if(query.getBuildingId() != null && !query.getBuildingId().equals("")) {
			whereSql.append(" AND buildingId = ?");
			listParam.add(query.getBuildingId());
		}
		
		if(query.getDomitoryId() != null && !query.getDomitoryId().equals("")) {
			whereSql.append(" AND r.domitoryId = ?");
			listParam.add(query.getDomitoryId());
		}
		
		if(query.getRepairContent() != null && !query.getRepairContent().equals("")) {
			whereSql.append(" AND r.RepairContent = ?");
			listParam.add(query.getRepairContent());
		}
		if(query.getPhone() != null && !query.getPhone().equals("")) {
			whereSql.append(" AND r.phone = ?");
			listParam.add(query.getPhone());
		}
		if(query.getStatus() != null && !query.getStatus().equals("")) {
			whereSql.append(" AND r.status = ?");
			listParam.add(query.getStatus());
		}
		
		if(query.getCreateDate() != null  && !query.getCreateDate().equals("")) {
			whereSql.append(" AND r.createDate = ?");
			listParam.add(query.getCreateDate());
		}
		if(query.getCreateId() != null  && !query.getCreateId().equals("")) {
			whereSql.append(" AND r.createId = ?");
			listParam.add(query.getCreateId());
		}
		
		if(query.getRepairId() != null) {
			whereSql.append(" AND r.RepairId = ?");
			listParam.add(query.getRepairId());
		}
		
		PageBean<RepairBean> pageBean = query.getPageBean();
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
	public int insert(RepairQuery query) {
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		List<Object> prarms = new ArrayList<Object>();
		
		prarms.add(query.getRepairId());
		prarms.add(query.getBuildingId());
		prarms.add(query.getDomitoryId());
		prarms.add(query.getRepairContent());
		prarms.add(query.getPhone());
		prarms.add(query.getStatus());
		prarms.add(query.getCreateDate());
		prarms.add(query.getCreateId());
	
		String sql = "insert into tRepair values(?,?,?,?,?,?,?,?)";
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
	public int deleteBatch(List<String> RepairIds) {
		int count = 0;
		con = getConnection();
		String sql = "delete from tRepair where RepairId = ?";
		Object[][] params = new Object[RepairIds.size()][];
		
		for(int i=0;i<RepairIds.size();++i) {
			params[i] = new Object[]{RepairIds.get(i)};
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
	public RepairBean getById(Integer RepairId) {
		String sql = "select * from tRepair where RepairId = ?";
		
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		RepairBean RepairBean = new RepairBean();
		try {
			RepairBean = queryRunner.query(con, sql, new BeanHandler<RepairBean>(RepairBean.class), RepairId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return RepairBean;
	}

	@Override
	public int update(RepairQuery query) {
		int count = 0;
		StringBuffer sql = new StringBuffer("update tRepair set ");
		
		query = appendUpdateSql(query);
		PageBean<RepairBean> pageBean = query.getPageBean();
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
	
	private RepairQuery  appendUpdateSql(RepairQuery query) {
		StringBuffer updateSql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();

		
		if(query.getBuildingId() != null) {
			updateSql.append(" buildingId = ? ,");
			params.add(query.getBuildingId());
		}
		
		if(query.getDomitoryId() != null) {
			updateSql.append(" domitoryId = ? ,");
			params.add(query.getDomitoryId());
		}
		
		if(query.getRepairContent() != null) {
			updateSql.append(" RepairContent = ? ,");
			params.add(query.getRepairContent());
		}
		
		if(query.getPhone() != null) {
			updateSql.append(" phone = ? ,");
			params.add(query.getPhone());
		}
		
		if(query.getStatus() != null) {
			updateSql.append(" status = ? ,");
			params.add(query.getStatus());
			
		}
		
		if(query.getCreateDate() != null) {
			updateSql.append(" createDate = ?,");
			params.add(query.getCreateDate());
		}
		
		if(query.getCreateId() != null) {
			updateSql.append(" createId = ?,");
			params.add(query.getCreateId());
		}
		
		updateSql.append(" repairId = repairId");
		
		updateSql.append(" where repairId = ? ");
		params.add(query.getRepairId());
		
		
		query.getPageBean().setSql(updateSql.toString());
		query.getPageBean().setSqlParam(params);
		
		return query;
	}
	

}
