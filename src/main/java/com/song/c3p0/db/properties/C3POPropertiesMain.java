package com.song.c3p0.db.properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3POPropertiesMain {

	public PreparedStatement getPrepareStatement(Connection conn,String sql){
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public PreparedStatement setPrepareStatementParameter(PreparedStatement ps,Object... values){
		try {
			if (null != values) {
				for (int i = 1; i <= values.length; i++) {
					ps.setObject(i, values[i - 1]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
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
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = C3P0Properties.getInstance().getConnection();
			
			String sql = "SELECT * FROM tb_user WHERE ID < ? ";
			
			C3POPropertiesMain c3p0Instance = new C3POPropertiesMain();
			
			ps = c3p0Instance.getPrepareStatement(conn, sql);
			c3p0Instance.setPrepareStatementParameter(ps, new Object[]{60});
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				Object obj1 = rs.getObject(1);
				Object obj4 = rs.getObject(4);
				
				System.out.println("ID: " + obj1 + ",NAME: " + obj4);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//释放资源
			C3POPropertiesMain.realeaseResource(rs, ps, conn);
		}
	}
}
