package com.cs.PairWork1.Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	private static  DBUtils dbUtils=new DBUtils();
	private static String dbUrl;
	public static DBUtils getInstance() {
		return dbUtils;
	}
	//@SuppressWarnings("unused")
	@SuppressWarnings("unused")
	public void init() {
		Properties p = new Properties();
		try {
			p.load(this.getClass().getResourceAsStream("/dbConn.property"));
			
			String dbDriver = p.getProperty("db.driver");
			
			Class.forName(dbDriver);
			
			DBUtils.dbUrl=p.getProperty("db.url");
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbUrl);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void releseAll(Connection conn,PreparedStatement pstm,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(pstm!=null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
	