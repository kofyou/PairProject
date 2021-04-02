package com.company.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

public class JDBCUtil {
	private static int initialConnectionCount = 20;
	private static String encoding = "UTF-8";
	private static LinkedList<Connection> pool = new LinkedList<>();

	private static Properties pro = new Properties();
	private static String driver;
	private static String loginName;
	private static String password;
	private static String url;

	static {

		try {
			pro.load(JDBCUtil.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver = pro.getProperty("driver");
		loginName = pro.getProperty("loginName");
		password = pro.getProperty("password");
		url = pro.getProperty("url");
		try {
			Class.forName(driver);
			for (int i = 0; i < initialConnectionCount; i ++) {
				Connection con = DriverManager.getConnection(url+"?encoding=UTF-8&serverTimezone=UTC",loginName,password);
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
