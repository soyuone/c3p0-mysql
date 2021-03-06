package com.song.c3p0.db.properties;

import java.sql.Connection;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Properties {

	private ComboPooledDataSource cpds;
	
	private static C3P0Properties c3P0Properties;
	
	static{
		c3P0Properties = new C3P0Properties();
	}

	public C3P0Properties() {
		try {
			cpds = new ComboPooledDataSource();
			
			//加载配置文件
			Properties props = new Properties();
			props.load(C3P0Properties.class.getClassLoader().getResourceAsStream("config.properties"));
			
			cpds.setDriverClass(props.getProperty("DriverClass"));
			cpds.setJdbcUrl(props.getProperty("JdbcUrl"));
			cpds.setUser(props.getProperty("User"));
			cpds.setPassword(props.getProperty("Password"));
			
			cpds.setMaxPoolSize(Integer.parseInt(props.getProperty("MaxPoolSize")));
			cpds.setMinPoolSize(Integer.parseInt(props.getProperty("MinPoolSize")));
			cpds.setInitialPoolSize(Integer.parseInt(props.getProperty("InitialPoolSize")));
			cpds.setMaxStatements(Integer.parseInt(props.getProperty("MaxStatements")));
			cpds.setMaxIdleTime(Integer.parseInt(props.getProperty("MaxIdleTime")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static C3P0Properties getInstance(){
		return c3P0Properties;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = cpds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
