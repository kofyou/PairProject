package edu.fzu.postsearch.util;

import edu.fzu.postsearch.dao.PostDAO;
import edu.fzu.postsearch.dao.PostDAOimpl;
import edu.fzu.postsearch.pojo.Post;

import java.sql.*;
import java.util.List;

public class DBUtil {
	static String loginName = "root";
	static String password = "root";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/lunwen?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		return DriverManager.getConnection(url, loginName, password);
	}

	/* 关闭连接的方法 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
		PostDAO postDAO = new PostDAOimpl();
		List<Post> posts = postDAO.listSearch("");
		System.out.println(posts.get(0).getTitle());
	}
}