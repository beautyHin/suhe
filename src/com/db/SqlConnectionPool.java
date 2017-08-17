package com.db;

import java.sql.Connection;
import java.sql.SQLException;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SqlConnectionPool {

	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	
	public static  Connection getConnection() {
		Connection con = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
