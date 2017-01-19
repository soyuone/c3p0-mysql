package com.song.c3p0.db.inner;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0InnerDispatcher {
	
	private static C3P0InnerDispatcher c3P0InnerDispatcher;
	private ComboPooledDataSource cpds;

	static {
		c3P0InnerDispatcher = new C3P0InnerDispatcher();
	}
	
	public C3P0InnerDispatcher() {
		try {
			cpds = new ComboPooledDataSource();
			
			cpds.setDriverClass("com.mysql.jdbc.Driver");//连接数据库所需驱动
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/cloudhospital");//连接数据库的URL
			cpds.setUser("root");//数据库用户名
			cpds.setPassword("admin");//数据库密码
			
			cpds.setMaxPoolSize(20);//连接池最大连接数
			cpds.setMinPoolSize(2);//连接池最小连接数
			cpds.setInitialPoolSize(5);//连接池初始连接数
			cpds.setMaxStatements(30);//连接池缓存Statement最大数
			cpds.setMaxIdleTime(100);//连接的最大空闲时间
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public static C3P0InnerDispatcher getInstance(){
		return c3P0InnerDispatcher;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = cpds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
