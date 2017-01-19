package com.song.c3p0.db.xml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class C3P0XmlSimplifyMain {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = C3P0XmlSimplify.getInstance().getConnection();
			
			String sql = " SELECT * FROM tb_user WHERE ID < ? ";
			ps = C3P0XmlSimplify.setStatement(conn, sql);
			ps = C3P0XmlSimplify.setSQLParameters(ps, new Object[]{20});
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				Object obj1 = rs.getObject(1);
				Object obj4 = rs.getObject(4);
				
				System.out.println("ID: " + obj1 + ",Name: " + obj4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			C3P0XmlSimplify.releaseSources(conn, ps, rs);
		}
	}
}
