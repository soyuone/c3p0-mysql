package com.song.c3p0.db.xml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0XmlMain {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			
			cpds = C3P0Xml.setParameters(cpds);
			
			conn = C3P0Xml.getConnection(cpds);
			
			String sql = " SELECT * FROM tb_user WHERE ID < ? ";
			ps = C3P0Xml.setStatements(conn, sql);
			
			ps = C3P0Xml.setSQLParameters(ps, new Object[]{20});
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				Object obj1 = rs.getObject(1);
				Object obj4 = rs.getObject(4);
				
				System.out.println("ID: " + obj1 + ",Name: " + obj4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			C3P0Xml.releaseSource(conn, ps, rs);
		}
	}
}
