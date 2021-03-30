package com.company.util;

import java.sql.*;
import java.util.LinkedList;

public class JDBCUtil {
	private static String driver = "com.mysql.cj.jdbc.Driver";
//	private static String ip = "39.102.39.208";
	private static String ip = "127.0.0.1";
	private static int port = 3306;
//	private static String database = "paperdatabase";
	private static String database = "javaee";
//	private static String loginName = "gz";
//	private static String password = "123456";
	private static String loginName = "root";
	private static String password = "root";
	private static String encoding = "UTF-8";
	private static int initialConnectionCount = 20;
	private static String url = String.format("jdbc:mysql://%s:%d/%s?encoding=%s&serverTimezone=UTC", ip, port, database,encoding);
	
	private static LinkedList<Connection> pool = new LinkedList<>();
	
	static {
		try {
			Class.forName(driver);
			for (int i = 0; i < initialConnectionCount; i ++) {
				Connection con = DriverManager.getConnection(url,loginName,password);
				pool.add(con);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if (pool.size()>0) {
			return pool.removeFirst();
		}
		else {
			Connection con = null;
			try {
				con = DriverManager.getConnection(url,loginName,password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
	}
	
	public static void release(Connection con, Statement st, ResultSet rs) {
		if (con != null) {
			pool.add(con);
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void release(Connection con, PreparedStatement st, ResultSet rs) {
		if (con != null) {
			pool.add(con);
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
