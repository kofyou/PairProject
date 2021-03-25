package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.PaperBean;
import util.JDBCUtil;

public class PaperDao {
	
	
	//查询是否存在于数据库中
	private boolean isExistDatabase(PaperBean paper) {
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "select * from paper where name=?";
		boolean isExist = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(0, paper.getName());
			isExist = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn,ps,null);
		}
		return isExist;
	}
	
	public void insertOne(PaperBean paper) {
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "insert into paper values(?,?,?,?,?,?)";
		if (!isExistDatabase(paper)) {
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(0, paper.getName());
				ps.setString(1, paper.getYear());
				ps.setString(2, paper.getMeeting());
				ps.setString(3, paper.getAbstractt());
				ps.setString(4, paper.getUrl());
				ps.setInt(5, paper.getAccesstimes());
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.release(conn, ps, null);
			}
		}
	}
	
	public void insertAll(LinkedList<PaperBean> papers) {
		for (PaperBean paper:papers) {
			insertOne(paper);
		}
	}
}
