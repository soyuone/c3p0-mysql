package com.song.c3p0.db.inner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Inner {
	
	private static ComboPooledDataSource ds;
	
	//静态初始化块进行初始化
	static{
		try {
			ds = new ComboPooledDataSource();//创建连接池实例
			
			ds.setDriverClass("com.mysql.jdbc.Driver");//设置连接池连接数据库所需的驱动
			
			ds.setJdbcUrl("jdbc:mysql://localhost:3306/cloudhospital");//设置连接数据库的URL
			
			ds.setUser("root");//设置连接数据库的用户名
			
			ds.setPassword("admin");//设置连接数据库的密码
			
			ds.setMaxPoolSize(40);//设置连接池的最大连接数
			
			ds.setMinPoolSize(2);//设置连接池的最小连接数
			
			ds.setInitialPoolSize(10);//设置连接池的初始连接数
			
			ds.setMaxStatements(100);//设置连接池的缓存Statement的最大数			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取与指定数据库的连接
	public static ComboPooledDataSource getInstance(){
		return ds;
	} 

	//从连接池返回一个连接
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//释放资源
	public static void realeaseResource(ResultSet rs,PreparedStatement ps,Connection conn){
		if(null != rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(null != ps){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
