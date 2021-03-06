package com.song.c3p0.db.properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0PropertiesSimplifyMain {

	public PreparedStatement createStatement(Connection conn,String sql){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public PreparedStatement setParameters(PreparedStatement ps,Object...values){
		try {
			if(null != values){
				for(int i=1;i<=values.length;i++){
					ps.setObject(i, values[i-1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = C3P0PropertiesSimplify.getInstance().getConnection();
			
			C3P0PropertiesSimplifyMain c3p0Instance = new C3P0PropertiesSimplifyMain();
			String sql = "SELECT * FROM tb_user WHERE ID < ? ";
			ps = c3p0Instance.createStatement(conn, sql);
			ps = c3p0Instance.setParameters(ps, new Object[]{20});
			
			rs = ps.executeQuery();
			while(rs.next()){
				Object obj1 = rs.getObject(1);
				Object obj4 = rs.getObject(4);
				
				System.out.println("ID: " + obj1 + ",Name: " + obj4);
			}
		} catch (Exception e) {
		}finally{
			try {
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
