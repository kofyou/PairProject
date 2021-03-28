package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sql = "select * from paper where name like '?'";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
	}
}
