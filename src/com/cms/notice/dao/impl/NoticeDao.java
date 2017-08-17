package com.cms.notice.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cms.notice.bean.NoticeBean;
import com.cms.notice.dao.INoticeDao;
import com.cms.notice.model.NoticeQuery;
import com.framework.common.BaseDao;
import com.framework.common.PageBean;

public class NoticeDao extends BaseDao implements INoticeDao {
	
private Connection con;
	 
	
	public List<NoticeBean> findList() {
		String sql = "SELECT * FROM tNotice";
		QueryRunner queryRunner = new QueryRunner();
		con = getConnection();
		List<NoticeBean> list =new ArrayList<NoticeBean>();
		try {
			list = queryRunner.query(con, sql,new BeanListHandler<>(NoticeBean.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int findCount(NoticeQuery query) {
		
		Number count = 0;
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM tNotice");
		
		PageBean<NoticeBean> pageBean = this.appendWhereSql(query,false);
		sql.append(pageBean.getSql());
		
		QueryRunner queryRunner = new QueryRunner();
		con = getConnection();
		
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
	public NoticeQuery findPage(NoticeQuery query) {
		List<NoticeBean> resultList = new ArrayList<NoticeBean>();
		StringBuffer sql = new StringBuffer("SELECT * FROM tNotice ");
		PageBean<NoticeBean> pageBean = query.getPageBean();
		
		int count = this.findCount(query);
		
		if(count > 0) {
			pageBean = this.appendWhereSql(query, true);
			sql.append(pageBean.getSql());
			
			System.out.println("SQL:"+sql);
			System.out.println("Param:"+pageBean.getSqlParam());
			
			con = getConnection();
			QueryRunner queryRunner = new QueryRunner();
			try {
				resultList = queryRunner.query(con, sql.toString(), new BeanListHandler<>(NoticeBean.class), pageBean.getSqlParam().toArray());
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

	private PageBean<NoticeBean> appendWhereSql(NoticeQuery query,boolean isLimit) {

		StringBuffer whereSql = new StringBuffer(" where 1=1 "); 
		List<Object> listParam = new ArrayList<Object>();
		if(query.getNoticeName() != null) {
			whereSql.append(" AND NoticeName like ?");
			listParam.add("%"+query.getNoticeName()+"%"); 
		}
		
		if(query.getNoticeInfo() !=null ) {
			whereSql.append(" AND NoticeInfo LIKE ?");
			listParam.add("%"+query.getNoticeInfo()+"%");
		}
		PageBean<NoticeBean> pageBean = query.getPageBean();
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
	public int insert(NoticeQuery query) {
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		List<Object> prarms = new ArrayList<Object>();
		prarms.add(query.getNoticeId());
		prarms.add(query.getNoticeName());
		prarms.add(query.getNoticeInfo());
		prarms.add(query.getCreateDate());
		prarms.add(query.getCreateId());
		String sql = "insert into tNotice values(?,?,?,?,?)";
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
	public int deleteBatch(List<String> NoticeIds) {
		int count = 0;
		con = getConnection();
		String sql = "delete from tNotice where NoticeId = ?";
		Object[][] params = new Object[NoticeIds.size()][];
		
		for(int i=0;i<NoticeIds.size();++i) {
			params[i] = new Object[]{NoticeIds.get(i)};
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
	public NoticeBean getById(String NoticeId) {
		String sql = "select * from tNotice where NoticeId = ?";
		
		con = getConnection();
		QueryRunner queryRunner = new QueryRunner();
		
		NoticeBean NoticeBean = new NoticeBean();
		try {
			NoticeBean = queryRunner.query(con, sql, new BeanHandler<NoticeBean>(NoticeBean.class), NoticeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con);
		}
		
		return NoticeBean;
	}

	@Override
	public int update(NoticeQuery query) {
		int count = 0;
		StringBuffer sql = new StringBuffer("update tNotice set ");
		
		query = appendUpdateSql(query);
		PageBean<NoticeBean> pageBean = query.getPageBean();
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
	
	private NoticeQuery  appendUpdateSql(NoticeQuery query) {
		StringBuffer updateSql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(query.getNoticeName() != null) {
			updateSql.append(" NoticeName = ?,");
			params.add(query.getNoticeName());
		}
		
		if(query.getNoticeInfo() != null) {
			updateSql.append(" NoticeInfo = ?,");
			params.add(query.getNoticeInfo());
		}
		
		if(query.getCreateDate() != null) {
			updateSql.append(" createDate = ?,");
			params.add(query.getCreateDate());
		}
		
		if(query.getCreateId() != null) {
			updateSql.append(" createId = ?,");
			params.add(query.getCreateId());
		}
		
		updateSql.append(" NoticeId = NoticeId");
		
		updateSql.append(" where NoticeId = ? ");
		params.add(query.getNoticeId());
		
		query.getPageBean().setSql(updateSql.toString());
		query.getPageBean().setSqlParam(params);
		
		return query;
	}
	

}
