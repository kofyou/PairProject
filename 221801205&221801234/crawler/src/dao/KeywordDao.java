package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.KeywordBean;
import util.JDBCUtil;

public class KeywordDao {
	
	private boolean isExistDabase(KeywordBean keyword) {
		boolean isExist = false;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "select * from keywords where keyword = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(0, keyword.getKeyword());
			isExist = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, null);
		}
		return isExist;
	}
	
	private void updateAppeartimes(KeywordBean keyword) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update keywords set appeartimes = appeartimes + 1 where keyword = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(0, keyword.getKeyword());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, null);
		}
	}
	
	private void insertOne(KeywordBean keyword) {
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into keywords values(?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(0, keyword.getKeyword());
			ps.setInt(0, 1);
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, null);
		}
	}
	//存在则出现次数+1，否则添加
	public void insertOrUpdateOne(KeywordBean keyword) {	
		if (isExistDabase(keyword)) {
			updateAppeartimes(keyword);
		}
		else {
			insertOne(keyword);
		}
	}
	
	public void insertOrUpdateAll(LinkedList<KeywordBean> keywords) {
		for (KeywordBean keyword:keywords) {
			insertOrUpdateOne(keyword);
		}
	}
}
