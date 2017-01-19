package com.song.c3p0.db.xml;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Xml {
	
	//解析xml文件
	public static ComboPooledDataSource setParameters(ComboPooledDataSource cpds){
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File("src/main/java/config.xml"));//读取XML文件,获得document对象 
			
			Element root = document.getRootElement();//获取文档的根节点
			Element ele = root.element("config");//取得某个节点的子节点
			
			String driverClass = ele.element("driverClass").getText();
			String jdbcUrl = ele.element("jdbcUrl").getText();
			String user = ele.element("user").getText();
			String password = ele.element("password").getText();
			String maxPoolSize = ele.element("maxPoolSize").getText();
			String minPoolSize = ele.element("minPoolSize").getText();
			String maxStatements = ele.element("maxStatements").getText();
			String maxIdleTime = ele.element("maxIdleTime").getText();
			
			cpds.setDriverClass(driverClass);
			cpds.setJdbcUrl(jdbcUrl);
			cpds.setUser(user);
			cpds.setPassword(password);
			cpds.setMaxPoolSize(Integer.parseInt(maxPoolSize));
			cpds.setMinPoolSize(Integer.parseInt(minPoolSize));
			cpds.setMaxStatements(Integer.parseInt(maxStatements));
			cpds.setMaxIdleTime(Integer.parseInt(maxIdleTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cpds;
	}
	
	public static Connection getConnection(ComboPooledDataSource cpds){
		Connection conn = null;
		try {
			conn = cpds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static PreparedStatement setStatements(Connection conn,String sql){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public static PreparedStatement setSQLParameters(PreparedStatement ps,Object...values){
		try {
			if (null != values) {
				for (int i = 1; i <= values.length; i++) {
					ps.setObject(i, values[i-1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public static void releaseSource(Connection conn, PreparedStatement ps, ResultSet rs){
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
		
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
