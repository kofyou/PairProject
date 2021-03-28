package dao;

import pojo.Keyword;
import pojo.Paper;
import pojo.YearFrequency;
import util.CountWordsUtil;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class PaperDAOImpl implements PaperDAO{

    //返回模糊查询到的论文总数
    public int getTotal(String str) {
        int total = 0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select count(*) from paper where title like '%" + str + "%' or summary like '%"
                    + str + "%' or keywords like '%" + str + "%'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return total;
    }

    //根据论文题目删除对应论文
    public void delete(String title) {
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "delete from paper where title='" + title + "'";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, stmt, conn);
        }
    }

    //模糊查询分页用
    public ArrayList<Paper> list(String str, int pageNum, int lineNum) {
        int start = (pageNum - 1) * lineNum;
        ArrayList<Paper> paperList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from paper where title like '%" + str + "%' or summary like '%"
                    + str + "%' or keywords like '%" + str + "%'"
                    + " limit " + start + "," + lineNum;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("title");
                String conference = rs.getString("conference");
                int year = rs.getInt("year");
                String summary = rs.getString("summary");
                String link = rs.getString("link");
                String keywords = rs.getString("keywords");

                Paper paper = new Paper();
                paper.setTitle(title);
                paper.setConference(conference);
                paper.setYear(year);
                paper.setSummary(summary);
                paper.setLink(link);
                paper.setKeywords(keywords);
                paperList.add(paper);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return paperList;
    }

    //根据论文题目返回对应论文
    public Paper get(String paperTitle) {
        Paper paper = new Paper();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from paper where title='" + paperTitle + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("title");
                String conference = rs.getString("conference");
                int year = rs.getInt("year");
                String summary = rs.getString("summary");
                String link = rs.getString("link");
                String keywords = rs.getString("keywords");

                paper.setTitle(title);
                paper.setConference(conference);
                paper.setYear(year);
                paper.setSummary(summary);
                paper.setLink(link);
                paper.setKeywords(keywords);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return paper;
    }

}
