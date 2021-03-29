package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import bean.PaperBean;
import bean.PaperKeywordBean;
import util.JDBCUtil;

public class PaperKeywordDao {
	public void insertOne(PaperKeywordBean paperKeywordBean) {
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into name_keyword(name,keyword) values(?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, paperKeywordBean.getName());
			ps.setString(2, paperKeywordBean.getKeyword());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, null);
		}
	}
	
	public void insertAll(LinkedList<PaperKeywordBean> beans) {
		for (PaperKeywordBean paperKeywordBean:beans) {
			insertOne(paperKeywordBean);
		}
	}
	
	public LinkedList<PaperKeywordBean> searchKeywordListByName(String name){
		LinkedList<PaperKeywordBean> beans = new LinkedList<PaperKeywordBean>();
		String sql = "select * from name_keyword where name = ?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				PaperKeywordBean bean = new PaperKeywordBean();
				bean.setName(name);
				bean.setKeyword(rs.getString("keyword"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return beans;
		
	}
	
	public LinkedList<String> searchPaperNameListBykeyword(String keyword){
		String sql = "select name from name_keyword where keyword = ?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		LinkedList<String> nameList = new LinkedList<String>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, keyword);
			rs = ps.executeQuery();
			while (rs.next()) {
				nameList.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return nameList;
	}
	
	
	public String[] searchTopKeywordByMnY(String meeting, String year){
		String sql = "select keyword "
				+ "from paper,name_keyword "
				+ "where paper.name = name_keyword.name and meeting = ? and year = ? "
				+ "group by(keyword) "
				+ "order by(count(keyword)) desc "
				+ "limit 3";
		String []keyword = new String[3];
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, meeting);
			ps.setString(2, year);
			rs = ps.executeQuery();
			for (int i = 0 ;rs.next(); i++) {
				keyword[i] = rs.getString("keyword");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return keyword;
	}
	
	public HashMap<String, Integer> searchYnCByMnKnYC(String meeting, String keyword, int yearCount){
		String sql = "select count(year) as count,year "
				+ "from paper,name_keyword "
				+ "where paper.name = name_keyword.name and meeting = ? and keyword = ? "
				+ "group by(year) "
				+ "order by(count) desc "
				+ "limit ?";
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, meeting);
			ps.setString(2, keyword);
			ps.setInt(3, yearCount);
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("year"), rs.getInt("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return map;
	}
}
