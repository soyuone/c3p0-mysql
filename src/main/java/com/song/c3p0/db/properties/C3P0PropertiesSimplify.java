package com.song.c3p0.db.properties;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0PropertiesSimplify {
	
	private static C3P0PropertiesSimplify c3P0PropertiesSimplify;
	
	private ComboPooledDataSource cpds;
	
	static{
		c3P0PropertiesSimplify = new C3P0PropertiesSimplify();
	}

	public C3P0PropertiesSimplify() {
		cpds = new ComboPooledDataSource();
	}
	
	public static C3P0PropertiesSimplify getInstance(){
		return c3P0PropertiesSimplify;
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
