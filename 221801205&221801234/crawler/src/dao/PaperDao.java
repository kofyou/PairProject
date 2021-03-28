package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import bean.PaperBean;
import util.JDBCUtil;

public class PaperDao {
	
	public boolean isExistDatabase(PaperBean paper) {
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		String sql = "select * from paper where name=?";
		boolean isExist = false;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, paper.getName());
			rs = ps.executeQuery();
			isExist = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn,ps,rs);
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
				ps.setString(1, paper.getName());
				ps.setString(2, paper.getYear());
				ps.setString(3, paper.getMeeting());
				ps.setString(4, paper.getAbstractt());
				ps.setString(5, paper.getUrl());
				ps.setInt(6, paper.getAccesstimes());
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
	
	//根据标题查询论文列表（模糊查询）
	public LinkedList<PaperBean> searchPaperListByName(String name){
		LinkedList<PaperBean> beans = new LinkedList<PaperBean>();
		String sql = "select * from paper where name like \"%\"?\"%\"";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//System.out.println("11");
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				PaperBean bean = new PaperBean();
				//System.out.println("123"+rs.getString("name"));
				bean.setName(rs.getString("name"));
				bean.setYear(rs.getString("year"));
				bean.setMeeting(rs.getString("meeting"));
				bean.setAbstractt(rs.getString("abstract"));
				bean.setUrl(rs.getString("url"));
				//accesstimes
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return beans;
	}
	
	public LinkedList<PaperBean> searchPaperListByNameList(LinkedList<String> nameList){
		LinkedList<PaperBean> paperList = new LinkedList<PaperBean>();
		for (String name:nameList) {
			PaperBean bean = searchPaperByName(name);
			paperList.add(bean);
		}
		return paperList;
	}
	
	public PaperBean searchPaperByName(String name){
		PaperBean bean = new PaperBean();
		String sql = "select * from paper where name = ?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//System.out.println("11");
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				//System.out.println("123"+rs.getString("name"));
				bean.setName(rs.getString("name"));
				bean.setYear(rs.getString("year"));
				bean.setMeeting(rs.getString("meeting"));
				bean.setAbstractt(rs.getString("abstract"));
				bean.setUrl(rs.getString("url"));
				//accesstimes
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.release(conn, ps, rs);
		}
		return bean;
	}
	
}
